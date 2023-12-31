package by.wolfcup.fakepostapp.android.screen.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import by.wolfcup.fakepostapp.android.screen.comments.CommentsSlotUi
import by.wolfcup.fakepostapp.views.Post
import by.wolfcup.fakepostapp.views.Root
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    component: Post,
    childSlot: ChildSlot<*, Root.SlotChild>,
) {
    val state = component.uiState.subscribeAsState().value
    Scaffold(
        topBar = {
            LargeTopAppBar(title = { 
                Text(text = state.data.title)
            },
                navigationIcon = {
                    IconButton(onClick = { component.navigateBack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                })
        },
        contentWindowInsets = WindowInsets(top = 70.dp, left = 15.dp, right = 15.dp)
    ) { pv ->
        Column(
            modifier = Modifier.padding(pv)
        ) {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Content",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = state.data.body.repeat(10)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Comments",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                childSlot.child?.instance?.also { slot ->
                    when(slot) {
                        is Root.SlotChild.CommentsChild -> CommentsSlotUi(component = slot.component)
                    }
                }
            }
        }
    }
}