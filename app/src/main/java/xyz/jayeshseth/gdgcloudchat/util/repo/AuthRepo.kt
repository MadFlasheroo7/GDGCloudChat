package xyz.jayeshseth.gdgcloudchat.util.repo

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow
import xyz.jayeshseth.gdgcloudchat.util.Response

interface AuthRepo {
    val isUserAuthenticatedInFirebase: Boolean
    val displayName: String
    val photoUrl: String

    fun oneTapSignInWithGoogle(): Flow<Response<BeginSignInResult>>

    fun signInWithGoogle(googleCredential: AuthCredential): Flow<Response<Boolean>>

    fun signOut(): Flow<Response<Boolean>>

    fun revokeAccess(): Flow<Response<Boolean>>
}