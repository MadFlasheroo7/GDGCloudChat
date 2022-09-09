package xyz.jayeshseth.gdgcloudchat.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.jayeshseth.gdgcloudchat.ui.screens.AuthScreen
import xyz.jayeshseth.gdgcloudchat.ui.screens.SignInButton

@Composable
fun AuthContent(
    padding: PaddingValues,
    oneTapSignIn: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(padding),
        contentAlignment = Alignment.Center
    ) {
        SignInButton(
            onClick = oneTapSignIn
        )
    }
}

@Preview
@Composable
fun PreviewAuth() {
    AuthContent(padding = PaddingValues(10.dp),
        oneTapSignIn = {}
    )
}

@Preview
@Composable
fun PreviewBtn() {
    SignInButton(onClick = {})
}