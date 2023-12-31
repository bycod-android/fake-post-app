package by.wolfcup.fakepostapp.views.state

import by.wolfcup.domain.model.Comment
import by.wolfcup.domain.model.Post


data class PostUiState(
    val data: Post = Post(),
    val isLoading: Boolean = false,
    val msg: String = "",
    val comments: List<Comment> = emptyList()
)
