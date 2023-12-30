package by.wolfcup.fakepostapp.di

import by.wolfcup.core.common.Constants
import by.wolfcup.domain.usecase.PostsUseCase
import by.wolfcup.network.FakePostsApi
import by.wolfcup.network.remote.Repository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::provideRootSourceUrl)
    singleOf(::provideFakeService)
    singleOf(::provideUseCasePosts)

}

private fun provideRootSourceUrl() = Constants.ROOT_SOURCE_URL
private fun provideFakeService(url: String): Repository = FakePostsApi(url)
private fun provideUseCasePosts(repo: Repository): PostsUseCase = PostsUseCase(repo)
