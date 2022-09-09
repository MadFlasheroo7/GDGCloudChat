package xyz.jayeshseth.gdgcloudchat.util.repo

import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import xyz.jayeshseth.gdgcloudchat.util.Constants.CREATED_AT
import xyz.jayeshseth.gdgcloudchat.util.Constants.DISPLAY_NAME
import xyz.jayeshseth.gdgcloudchat.util.Constants.EMAIL
import xyz.jayeshseth.gdgcloudchat.util.Constants.NO_DISPLAY_NAME
import xyz.jayeshseth.gdgcloudchat.util.Constants.PHOTO_URL
import xyz.jayeshseth.gdgcloudchat.util.Constants.SIGN_IN_REQUEST
import xyz.jayeshseth.gdgcloudchat.util.Constants.SIGN_UP_REQUEST
import xyz.jayeshseth.gdgcloudchat.util.Constants.USERS
import xyz.jayeshseth.gdgcloudchat.util.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private var signInClient: GoogleSignInClient,
    private var db: FirebaseFirestore
) : AuthRepo {
    override val isUserAuthenticatedInFirebase = auth.currentUser != null
    override val displayName = auth.currentUser?.displayName ?: NO_DISPLAY_NAME
    override val photoUrl = auth.currentUser?.photoUrl.toString()

    override fun oneTapSignInWithGoogle() = flow {
        try {
            emit(Response.Loading)
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            emit(Response.Success(signInResult))
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                emit(Response.Success(signUpResult))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }
    }

    override fun signInWithGoogle(googleCredential: AuthCredential) = flow {
        try {
            Log.d("signIn", "signin initiated")
            emit(Response.Loading)
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }

    override fun signOut() = flow {
        try {
            emit(Response.Loading)
            oneTapClient.signOut()
            auth.signOut()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun revokeAccess() = flow {
        try {
            emit(Response.Loading)
            auth.currentUser?.apply {
                db.collection(USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to serverTimestamp()
)