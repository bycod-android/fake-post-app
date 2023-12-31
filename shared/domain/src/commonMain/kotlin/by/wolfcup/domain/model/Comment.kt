package by.wolfcup.domain.model

import by.wolfcup.network.dto.CommentDto

data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)

fun CommentDto.toComment() = Comment(body, email, id, name, postId)