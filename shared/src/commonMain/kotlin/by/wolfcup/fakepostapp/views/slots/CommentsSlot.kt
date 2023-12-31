package by.wolfcup.fakepostapp.views.slots

import by.wolfcup.core.common.Result
import by.wolfcup.domain.usecase.CommentsUseCase
import by.wolfcup.fakepostapp.views.state.CommentUiState
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

interface Comments {
    val commentUiState: Value<CommentUiState>
}

class CommentsSlot(
    private val postId: Int,
    componentContext: ComponentContext
) : Comments, ComponentContext by componentContext {
    private val viewModel = instanceKeeper.getOrCreate {
        CommentsViewModel(postId)
    }
    override val commentUiState: Value<CommentUiState>
        get() = viewModel.state

    private class CommentsViewModel(postId: Int) : InstanceKeeper.Instance, KoinComponent {
        private val _state = MutableValue(CommentUiState())
        val state = _state

        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        val commentsUseCase : CommentsUseCase = get()
        init {
            getCommentsToPost(postId)
        }

        private fun getCommentsToPost(postId: Int) = commentsUseCase(postId).onEach { result ->
            when(result) {
                is Result.Failure -> _state.update { it.copy(
                    isLoading = false,
                    msg = result.msg ?: "Fatal Error"
                ) }
                is Result.Loading -> _state.update { it.copy(
                    isLoading = true
                ) }
                is Result.Successful -> _state.update { it.copy(
                    isLoading = false,
                    data = result.data!!
                ) }
            }
        }.launchIn(scope)
    }


}