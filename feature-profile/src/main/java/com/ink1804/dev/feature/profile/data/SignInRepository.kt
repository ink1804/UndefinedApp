package com.ink1804.dev.feature.profile.data

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.ink1804.dev.common.ui.ActivityContext
import timber.log.Timber


interface SignInRepository {
    fun getLastSignedInAccount(): GoogleSignInAccount?
    fun getGoogleSignInClient(): GoogleSignInClient
    fun handleGoogleLogin(data: Intent?)
    fun signOut()
    fun revokeAccess()
}

class SignInRepositoryImpl(
    private val activityContext: ActivityContext
) : SignInRepository {

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

    override fun handleGoogleLogin(data: Intent?) {
        try {
            GoogleSignIn
                .getSignedInAccountFromIntent(data)
                .getResult(ApiException::class.java)
                ?.let { singInData -> //todo delete after test instagram login in fb (PRQA-6083)
                    singInData.email?.let { Timber.wtf("email: $it") }
                    singInData
                }
                ?.idToken
                ?.let { token -> Timber.wtf("token: $token") }
        } catch (error: ApiException) {
            Timber.wtf("err: $error")
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


