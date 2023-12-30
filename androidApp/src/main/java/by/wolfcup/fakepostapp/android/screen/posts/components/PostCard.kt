package by.wolfcup.fakepostapp.android.screen.posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import by.wolfcup.domain.model.Post

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: Post
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = modifier.padding(10.dp)
        )
        Text(
            text = post.body,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(start = 15.dp, end = 10.dp, bottom = 5.dp)
        )
    }
}