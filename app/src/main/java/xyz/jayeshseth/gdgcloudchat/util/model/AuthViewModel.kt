package xyz.jayeshseth.gdgcloudchat.util.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.jayeshseth.gdgcloudchat.util.Response
import xyz.jayeshseth.gdgcloudchat.util.Response.Success
import xyz.jayeshseth.gdgcloudchat.util.repo.AuthRepo
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepo,
    val oneTapSignIn: SignInClient
) : ViewModel() {
    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase
    val displayName get() = repo.displayName

    var oneTapSignInResponse by
    mutableStateOf<Response<BeginSignInResult>>(Success(null))
        private set
    var signInWithGoogleResponse by
    mutableStateOf<Response<Boolean>>(Success(false))
        private set
    var signOutResponse by
    mutableStateOf<Response<Boolean>>(Success(false))
        private set
    var revokeAccessResponse by
    mutableStateOf<Response<Boolean>>(Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        repo.oneTapSignInWithGoogle().collect { response ->
            oneTapSignInResponse = response
        }
    }

    fun signOut() = viewModelScope.launch {
        repo.signOut().collect { response ->
            signOutResponse = response
        }
    }

    fun revokeAccess() = viewModelScope.launch {
        repo.revokeAccess().collect { response ->
            revokeAccessResponse = response
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        repo.signInWithGoogle(googleCredential).collect { response ->
            signInWithGoogleResponse = response
        }
    }
}