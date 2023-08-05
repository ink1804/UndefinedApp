package com.ink1804.dev.feature.profile.data

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.ink1804.dev.common.ui.ActivityContext
import timber.log.Timber

interface GoogleSignInManager {
    fun getLastSignedInAccount(): GoogleSignInAccount?
    fun getGoogleSignInClient(): GoogleSignInClient
    suspend fun handleGoogleLogin(data: Intent?): GoogleSignInResult
    fun signOut()
    fun revokeAccess()
}

internal class GoogleSignInManagerImpl(
    private val activityContext: ActivityContext
) : GoogleSignInManager {

    override fun getLastSignedInAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(activityContext)
    }

    override fun getGoogleSignInClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(idToken)
            .requestProfile()
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activityContext, gso)
    }

    override suspend fun handleGoogleLogin(data: Intent?): GoogleSignInResult {
        var email: String? = null
        return try {
            GoogleSignIn
                .getSignedInAccountFromIntent(data)
                .getResult(ApiException::class.java)
                ?.let { singInData ->
                    singInData.email?.let {
                        GoogleSignInResult(it, "token")
                    }

                } ?: throw Error("hgl52", "qwe")
//                ?.idToken
//                ?.let { token ->
//                } ?: throw Error("hgl52", "qwe")
        } catch (error: ApiException) {
            Timber.wtf("err: $error")
            throw Error("hgl52", "Api")
        }
    }

    override fun signOut() {
        val googleSignInClient = getGoogleSignInClient()
        googleSignInClient.signOut()
            .addOnCompleteListener {
                Timber.wtf("completed")
            }
    }

    override fun revokeAccess() {
        getGoogleSignInClient().revokeAccess()
            .addOnCompleteListener {
                Timber.wtf("revoked")
            }
    }
}

data class GoogleSignInResult(
    val email: String,
    val token: String
)

open class Error(val errCode: String, val text: String) : Throwable(text)


