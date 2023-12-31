package by.wolfcup.fakepostapp.android.screen.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import by.wolfcup.fakepostapp.android.screen.posts.components.PostCard
import by.wolfcup.fakepostapp.views.Posts
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    component: Posts,
    provideId: (Int) -> Unit
) {
    val state = component.uiState.subscribeAsState().value
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Fake Posts App")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Edit, null)
                    }
                }
            )
        }
    ) { pv ->
        LazyColumn(
            contentPadding = pv,
        ) {
            items(state.data, key = { it.id } ) { post ->
                PostCard(post = post, onTap = provideId)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}