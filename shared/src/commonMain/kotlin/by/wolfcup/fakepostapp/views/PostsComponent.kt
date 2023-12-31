package by.wolfcup.fakepostapp.views

import by.wolfcup.core.common.Result
import by.wolfcup.domain.usecase.PostsUseCase
import by.wolfcup.fakepostapp.views.state.PostsUiState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


interface Posts {
    val uiState: Value<PostsUiState>
}
class PostsComponent(
    componentContext: ComponentContext
) : Posts, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate {
        PostsViewModel()
    }
    override val uiState: Value<PostsUiState>
        get() = viewModel.state


    class PostsViewModel() : InstanceKeeper.Instance, KoinComponent {
        private val _state = MutableValue(PostsUiState())
        val state: Value<PostsUiState> = _state
        private val useCase: PostsUseCase = get()

        private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        init {
            loadPostsFromNetwork()

        }
        private fun loadPostsFromNetwork() = useCase().onEach { result ->
            when(result) {
                is Result.Failure -> _state.update { it.copy(
                    isLoading = false,
                    msg = result.msg ?: "Fatal error"
                ) }
                is Result.Loading -> _state.update { it.copy(
                    isLoading = true
                ) }
                is Result.Successful -> _state.update { it.copy(
                    isLoading = false,
                    data = result.data ?: emptyList()
                ) }
            }
        }.launchIn(scope)
    }
}