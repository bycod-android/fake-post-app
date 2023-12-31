package by.wolfcup.domain.usecase

import by.wolfcup.core.common.Result
import by.wolfcup.domain.model.Post
import by.wolfcup.domain.model.toPost
import by.wolfcup.network.remote.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostUseCase(
    private val repo: Repository
) {
    operator fun invoke(id: Int): Flow<Result<Post>> = flow {
        println("Provide new id: $id")
        emit(Result.Loading())
        try {
            val post = repo.getPost(id).toPost()
            emit(Result.Successful(post))
        } catch (e: Exception) {
            emit(Result.Failure(e.message ?: "Fatal error"))
        }
    }
}