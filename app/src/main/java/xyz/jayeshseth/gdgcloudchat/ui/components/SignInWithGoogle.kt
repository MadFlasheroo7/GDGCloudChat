package xyz.jayeshseth.gdgcloudchat.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.jayeshseth.gdgcloudchat.util.Response
import xyz.jayeshseth.gdgcloudchat.util.model.AuthViewModel

@Composable
fun SignInWithGoogle(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToChatScreen: @Composable (signedIn: Boolean) -> Unit
) {
    when (val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Response.Failure -> LaunchedEffect(Unit) { print(signInWithGoogleResponse.err) }
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInWithGoogleResponse.data?.let { signedIn ->
            navigateToChatScreen(signedIn)
        }
    }
}