package by.wolfcup.network.remote

import by.wolfcup.network.dto.CommentDto
import by.wolfcup.network.dto.FakePostDto

interface Repository {
    suspend fun getAllPosts(): List<FakePostDto>
    suspend fun getPost(id: Int): FakePostDto

    suspend fun getCommentByPost(postId: Int): List<CommentDto>
}