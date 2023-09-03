package com.ink1804.dev.feature.profile.ui

import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.ink1804.dev.common.ui.base.FlowViewModel
import com.ink1804.dev.feature.profile.data.GoogleSignInManager
import com.ink1804.dev.feature.profile.navigation.ProfileCoordinator
import kotlinx.coroutines.launch

class ProfileViewModel(
    coordinator: ProfileCoordinator,
    private val googleSignInManager: GoogleSignInManager
) : FlowViewModel(),
    ProfileCoordinator by coordinator {

    //todo move to flowViewModel
    val isAuthorized = mutableStateOf(false)

    val signInFlow = sharedFlow<Intent>()

    init {
        isAuthorized.value = getLastSignedInAccount() != null
    }

    fun handleGoogleLogin(data: Intent?) {
        viewModelScope.launch {
            googleSignInManager.handleGoogleLogin(data)
            isAuthorized.value = true
        }
    }

    private fun getLastSignedInAccount(): GoogleSignInAccount? {
        return googleSignInManager.getLastSignedInAccount()
    }

    private fun getGoogleSignInClient(): GoogleSignInClient {
        return googleSignInManager.getGoogleSignInClient()
    }

    private fun signOut() {
        googleSignInManager.signOut()
    }

    private fun revokeAccess() {
        googleSignInManager.revokeAccess()
    }

    fun onSignInClick() {
        if (isAuthorized.value) {
            signOut()
            isAuthorized.value = false
        } else {
            val googleSignInClient = getGoogleSignInClient()
            viewModelScope.launch {
                signInFlow.emit(googleSignInClient.signInIntent)
            }
        }
    }
}

