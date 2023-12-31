package by.wolfcup.fakepostapp.android

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import by.wolfcup.fakepostapp.android.screen.posts.PostsScreen
import by.wolfcup.fakepostapp.views.Root
import by.wolfcup.fakepostapp.views.RootComponent
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun App(root: RootComponent) {
    val childStack by root.childStack.subscribeAsState()
    MaterialTheme {
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when(val instance = child.instance) {
                is Root.Child.PostsChild -> PostsScreen(
                    component = instance.component,
                    provideId = {
                        instance.component.openPostById(it)
                    }
                )
            }
        }
    }
}