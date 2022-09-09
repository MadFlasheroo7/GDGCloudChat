package xyz.jayeshseth.gdgcloudchat.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import xyz.jayeshseth.gdgcloudchat.util.Response.*
import xyz.jayeshseth.gdgcloudchat.util.model.AuthViewModel

@Composable
fun OneTapSingIn(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: @Composable (result: BeginSignInResult) -> Unit
) {
    when (val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Loading -> ProgressBar()
        is Success -> oneTapSignInResponse.data?.let {
            Log.d("signIn", "success")
            launch(it)
        }
        is Failure -> LaunchedEffect(Unit) {
            Log.d("signIn", "${oneTapSignInResponse.err}")
            print(oneTapSignInResponse.err)
        }
    }
}