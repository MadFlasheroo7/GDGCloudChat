//@file:OptIn(ExperimentalMaterial3Api::class)

package xyz.jayeshseth.gdgcloudchat.ui.screens

import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import xyz.jayeshseth.gdgcloudchat.R
import xyz.jayeshseth.gdgcloudchat.ui.components.*
import xyz.jayeshseth.gdgcloudchat.util.Response
import xyz.jayeshseth.gdgcloudchat.util.model.AuthViewModel

private lateinit var auth: FirebaseAuth
private lateinit var signInRequest: BeginSignInRequest
private lateinit var oneTapSignIn: SignInClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToChatScreen: () -> Unit
) {
    Scaffold(
        topBar = { GdgChatAppBar(title = { Text("Auth Screen") }) },
        content = { padding ->
            AuthContent(
                padding = padding,
                oneTapSignIn = { viewModel.oneTapSignIn() }
            )
        }
    )
    auth = FirebaseAuth.getInstance()
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    Log.d("signIn", "launcher initiated")

                    val credentials =
                        viewModel.oneTapSignIn.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = getCredential(googleIdToken, null)
                    viewModel.signInWithGoogle(googleCredentials)
                } catch (it: ApiException) {
                    print(it)
                }
            }
        }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(
            signInResult.pendingIntent.intentSender
        ).build()
        launcher.launch(intent)
    }
    OneTapSingIn {
        LaunchedEffect(it) {
            launch(it)
        }
    }
    SignInWithGoogle { signedIn ->
        if (signedIn) {
            LaunchedEffect(signedIn) {
                navigateToChatScreen()
            }
        }
    }
}

@Composable
fun SignInButton(onClick: () -> Unit) {
    ElevatedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp)
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.google_logo
            ), contentDescription = "",
            modifier = Modifier
                .size(45.dp)
                .padding(end = 10.dp)
        )
        Text(
            text = "Sign In With Google",
            fontSize = 18.sp
        )
    }
}
