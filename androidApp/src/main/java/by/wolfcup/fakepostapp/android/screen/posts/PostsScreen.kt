package by.wolfcup.fakepostapp.android.screen.posts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.wolfcup.fakepostapp.android.screen.posts.components.PostCard
import by.wolfcup.fakepostapp.views.Posts
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun PostsScreen(
    component: Posts,
    paddingValues: PaddingValues
) {
    val state = component.uiState.subscribeAsState().value

    LazyColumn(
        contentPadding = paddingValues
    ) {
        items(state.data, key = { it.id } ) { post ->
            PostCard(post = post)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}