package by.wolfcup.fakepostapp.views

import by.wolfcup.domain.usecase.PostsUseCase
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface Root {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class PostsChild(val component: Posts): Child()
    }
}

class RootComponent(
    componentContext: ComponentContext,
) : Root, ComponentContext by componentContext {

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, Root.Child>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        initialStack = { listOf(ScreenConfig.Posts) },
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: ScreenConfig,
        componentContext: ComponentContext
    ): Root.Child {
        return when(config) {
            is ScreenConfig.Posts -> Root.Child.PostsChild(
                PostsComponent(
                    componentContext = componentContext,
                    navigateToPost = {  }
                )
            )
        }
    }

    @Serializable
    sealed interface ScreenConfig {
        @Serializable
        data object Posts: ScreenConfig
    }
}