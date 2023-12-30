package by.wolfcup.network.remote

import by.wolfcup.network.dto.FakePostDto

interface Repository {
    suspend fun getAllPosts(): List<FakePostDto>
    suspend fun getPost(id: Int): FakePostDto
}