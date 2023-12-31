package by.wolfcup.fakepostapp.android.screen.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import by.wolfcup.fakepostapp.views.slots.Comments
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

fun LazyListScope.CommentsSlotUi(
    component: Comments,
) {
    val state = component.commentUiState.value
        items(state.data) { comment ->
            ListItem(headlineContent = {
                Column {
                    Text(
                        text = "c/ ${comment.name}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(text = "<${comment.email}>")
                }
            },
                supportingContent = {
                    Text(text = comment.body)
                })
        }
}