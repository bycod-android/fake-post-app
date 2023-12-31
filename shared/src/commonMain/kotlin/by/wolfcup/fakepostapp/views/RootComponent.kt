package by.wolfcup.fakepostapp.views

import by.wolfcup.fakepostapp.views.slots.Comments
import by.wolfcup.fakepostapp.views.slots.CommentsSlot
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface Root {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class PostsChild(val component: Posts) : Child()
        data class PostChild(val component: Post) : Child()
    }

    val childSlot: Value<ChildSlot<*, SlotChild>>

    sealed class SlotChild {
        data class CommentsChild(val component: Comments): SlotChild()
    }
}

class RootComponent(
    componentContext: ComponentContext,
) : Root, ComponentContext by componentContext {

    private val navigation = StackNavigation<ScreenConfig>()
    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val childStack: Value<ChildStack<*, Root.Child>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        initialStack = { listOf(ScreenConfig.Posts) },
        handleBackButton = true,
        childFactory = ::child
    )
    override val childSlot: Value<ChildSlot<*, Root.SlotChild>> = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        key = "comments-slot",
        childFactory = ::slotChild
    )

    private fun slotChild(
        config: SlotConfig,
        componentContext: ComponentContext
    ): Root.SlotChild {
        return when(config) {
            is SlotConfig.Comments -> Root.SlotChild.CommentsChild(
                CommentsSlot(
                    componentContext = componentContext,
                    postId = config.postId
                )
            )
        }
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun child(
        config: ScreenConfig,
        componentContext: ComponentContext
    ): Root.Child {
        return when(config) {
            is ScreenConfig.Posts -> Root.Child.PostsChild(
                PostsComponent(
                    componentContext = componentContext,
                    navigateToPost = {
                        navigation.pushNew(ScreenConfig.Post(it))
                        slotNavigation.activate(SlotConfig.Comments(it))
                    }
                )
            )

            is ScreenConfig.Post -> Root.Child.PostChild(
                PostComponent(
                    componentContext = componentContext,
                    navBack = { navigation.pop() },
                    id = config.id
                )
            )
        }
    }

    @Serializable
    sealed interface ScreenConfig {
        @Serializable
        data object Posts : ScreenConfig
        @Serializable
        data class Post(val id: Int) : ScreenConfig
    }

    @Serializable
    sealed interface SlotConfig {
        @Serializable
        data class Comments(val postId: Int): SlotConfig
    }
}