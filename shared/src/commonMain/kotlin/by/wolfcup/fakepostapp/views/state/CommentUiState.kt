package by.wolfcup.fakepostapp.views.state

import by.wolfcup.domain.model.Comment

data class CommentUiState(
    val data: List<Comment> = emptyList(),
    val msg: String = "",
    val isLoading: Boolean = false
)
