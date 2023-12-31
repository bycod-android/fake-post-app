package by.wolfcup.domain.usecase

import by.wolfcup.domain.model.Comment
import by.wolfcup.network.remote.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import by.wolfcup.core.common.Result
import by.wolfcup.domain.model.toComment

class CommentsUseCase(
    private val repo: Repository
) {
    operator fun invoke(postId: Int): Flow<Result<List<Comment>>> = flow {
        emit(Result.Loading())
        try {
            val comments = repo.getCommentByPost(postId).map { it.toComment() }
            emit(Result.Successful(comments))
        } catch (e: Exception) {
            emit(Result.Failure(e.message ?: "Fatal Error"))
        }
    }
}