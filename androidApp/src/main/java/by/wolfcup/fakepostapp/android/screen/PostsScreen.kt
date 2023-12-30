package by.wolfcup.fakepostapp.android.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import by.wolfcup.fakepostapp.views.Posts
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun PostsScreen(
    component: Posts
) {
    val state = component.uiState.subscribeAsState().value

    LazyColumn {
        items(state.data) { post ->
            Text(text = post.title)
        }
    }
}