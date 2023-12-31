package by.wolfcup.fakepostapp.views

import by.wolfcup.core.common.Result
import by.wolfcup.domain.usecase.PostUseCase
import by.wolfcup.fakepostapp.views.state.PostUiState
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

interface Post {
    val uiState: Value<PostUiState>
    fun navigateBack()
}
class PostComponent(
    componentContext: ComponentContext,
    val id: Int,
    private val navBack: () -> Unit
): Post, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate {
        PostViewModel(id)
    }

    override val uiState: Value<PostUiState>
        get() = viewModel.state

    private class PostViewModel(id: Int) : InstanceKeeper.Instance, KoinComponent {
        private val _state = MutableValue(PostUiState())
        val state: Value<PostUiState> = _state


        val postUseCase : PostUseCase = get()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        init {
            getLoadPost(id = id)
        }

        private fun getLoadPost(id: Int) = postUseCase(id).onEach { result ->
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
                    data = result.data!!
                ) }
            }
        }.launchIn(scope = scope)


    }

    override fun navigateBack() {
        navBack()
    }

}