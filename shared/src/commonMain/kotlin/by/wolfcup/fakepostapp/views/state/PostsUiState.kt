package by.wolfcup.fakepostapp.views.state

import by.wolfcup.domain.model.Post

data class PostsUiState(
    val data: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val msg: String = ""
)
