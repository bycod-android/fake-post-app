package by.wolfcup.domain.model

import by.wolfcup.network.dto.FakePostDto

data class Post(
    val id: Int = -100,
    val userId: Int = -100,
    val title: String = "No Signal",
    val body: String = "No Signal",
)

fun FakePostDto.toPost(): Post = Post(id, userId, title, body)