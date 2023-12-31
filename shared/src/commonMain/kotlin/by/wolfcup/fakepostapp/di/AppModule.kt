package by.wolfcup.fakepostapp.di

import by.wolfcup.core.common.Constants
import by.wolfcup.domain.usecase.CommentsUseCase
import by.wolfcup.domain.usecase.PostUseCase
import by.wolfcup.domain.usecase.PostsUseCase
import by.wolfcup.network.FakePostsApi
import by.wolfcup.network.remote.Repository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::provideRootSourceUrl)
    singleOf(::provideFakeService)
    singleOf(::provideUseCasePosts)
    singleOf(::provideUseCasePost)
    singleOf(::provideUseCaseCommentsByPost)

}

private fun provideRootSourceUrl() = Constants.ROOT_SOURCE_URL
private fun provideFakeService(url: String): Repository = FakePostsApi(url)
private fun provideUseCasePosts(repo: Repository): PostsUseCase = PostsUseCase(repo)
private fun provideUseCasePost(repo: Repository): PostUseCase = PostUseCase(repo)

private fun provideUseCaseCommentsByPost(repo: Repository): CommentsUseCase = CommentsUseCase(repo)
