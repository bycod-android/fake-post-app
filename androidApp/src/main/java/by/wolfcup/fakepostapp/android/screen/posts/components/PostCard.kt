package by.wolfcup.fakepostapp.android.screen.posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.wolfcup.domain.model.Post
import coil.compose.AsyncImage

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: Post,
    onTap: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onTap(post.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://th.bing.com/th/id/OIG.KsNk.SJ9jJwttv3SxWu6?pid=ImgGn",
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.weight(.7f)
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
    }
}

@Preview
@Composable
fun PostPreview() {
   MaterialTheme {
       PostCard(post = Post(0, 0, "Post Title", "Post body"), onTap = {})
   }
}