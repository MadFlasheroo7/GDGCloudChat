@file:OptIn(ExperimentalMaterial3Api::class)

package xyz.jayeshseth.gdgcloudchat.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.jayeshseth.gdgcloudchat.ui.components.GdgChatAppBar
import xyz.jayeshseth.gdgcloudchat.util.Constants
import xyz.jayeshseth.gdgcloudchat.util.model.ChatViewModel


@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = viewModel()
) {
    val message: String by chatViewModel.message.observeAsState(initial = "")
    val messages: List<Map<String, Any>> by chatViewModel.messages.observeAsState(
        initial = emptyList<Map<String, Any>>().toMutableList()
    )
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(topBarState) }
    Scaffold(
        topBar = {
            ChatNameBar(
                chatName = "GDG Cloud Chat",
                scrollBehavior = scrollBehavior,
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            OutlinedTextField(
                value = message,
                onValueChange = {
                    chatViewModel.updateMessage(it)
                },
                label = {
                    Text(
                        "Type Your Message"
                    )
                },
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 1.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            chatViewModel.sendMessage()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send Button"
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 0.85f, fill = true),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    reverseLayout = true
                ) {
                    items(messages) { message ->
                        val isCurrentUser = message[Constants.IS_CURRENT_USER] as Boolean

                        Message(
                            message = message[Constants.MESSAGE].toString(),
                            isCurrentUser = isCurrentUser
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun Messages(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
    chatViewModel: ChatViewModel = viewModel()
) {
    val messages: List<Map<String, Any>> by chatViewModel.messages.observeAsState(
        initial = emptyList<Map<String, Any>>().toMutableList()
    )
    Column(
        modifier = modifier.padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            reverseLayout = true,
            state = scrollState,
            contentPadding = WindowInsets.statusBars.add(WindowInsets(top = 90.dp))
                .asPaddingValues(),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(messages) { message ->
                val isCurrentUser = message[Constants.IS_CURRENT_USER] as Boolean
                Message(
                    message = message[Constants.MESSAGES] as String,
                    isCurrentUser = isCurrentUser
                )
            }
        }
    }
}

@Composable
fun Message(message: String, isCurrentUser: Boolean) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(
                color = if (isCurrentUser) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(10.dp),
            ),
    ) {
        Text(
            text = message,
            textAlign =
            if (isCurrentUser)
                TextAlign.End
            else
                TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = if (isCurrentUser) MaterialTheme.colorScheme.onPrimary else Color.White
        )
    }
}

@Composable
fun ChatNameBar(
    chatName: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {

    GdgChatAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = chatName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        actions = {
            Button(
                contentPadding = PaddingValues(10.dp),
                onClick = {}
            ) {
                Text(
                    "Sign Out",
                )
            }
        }
    )
}