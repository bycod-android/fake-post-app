package by.wolfcup.domain.usecase

import by.wolfcup.core.common.Result
import by.wolfcup.domain.model.Post
import by.wolfcup.network.dto.FakePostDto
import by.wolfcup.network.remote.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsUseCase (
    private val repo: Repository
) {
    operator fun invoke(): Flow<Result<List<Post>>> = flow {
        emit(Result.Loading())
        try {
            val posts = repo.getAllPosts().map { it.toPost() }
            emit(Result.Successful(posts))
        } catch (e: Error) {
            emit(Result.Failure(e.message ?: "error msg"))
        }

    }

    private fun FakePostDto.toPost(): Post = Post(id, userId, title, body)
}