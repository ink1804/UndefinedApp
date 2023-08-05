package com.ink1804.dev.feature.profile.ui

import android.content.Intent
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.ink1804.dev.common.ui.base.FlowViewModel
import com.ink1804.dev.feature.profile.data.GoogleSignInManager
import com.ink1804.dev.feature.profile.navigation.ProfileCoordinator
import kotlinx.coroutines.launch
import timber.log.Timber

class ProfileViewModel(
    coordinator: ProfileCoordinator,
    private val googleSignInManager: GoogleSignInManager
) : FlowViewModel(),
    ProfileCoordinator by coordinator {

    //todo move to flowViewModel
    val menuItems = mutableStateListOf<ProfileListItem>()

    val signInFlow = sharedFlow<Intent>()

    init {
        menuItems.addAll(
            listOf(
                ProfileListItem(ProfileListItemType.Settings),
                ProfileListItem(ProfileListItemType.About),
                ProfileListItem(ProfileListItemType.RateUs),
                if (getLastSignedInAccount() == null)
                    ProfileListItem(ProfileListItemType.SignIn)
                else
                    ProfileListItem(ProfileListItemType.SignOut),
            )
        )
    }

    fun handleGoogleLogin(data: Intent?) {
        viewModelScope.launch {
            menuItems.indexOfFirst {
                it.type == ProfileListItemType.SignIn || it.type == ProfileListItemType.SignOut
            }.let { index ->
                val newItem = try {
                    Timber.tag("myLogs").wtf("success")
                    googleSignInManager.handleGoogleLogin(data)
                    ProfileListItem(ProfileListItemType.SignOut)
                } catch (e: Throwable) {
                    Timber.tag("myLogs").wtf("fail $e")
                    ProfileListItem(ProfileListItemType.SignIn)
                }

                if (index != -1) menuItems[index] = newItem
            }
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
        menuItems.indexOfFirst { it.type == ProfileListItemType.SignOut }
            .let {
                if (it != -1) menuItems[it] = ProfileListItem(ProfileListItemType.SignIn)
            }
    }

    private fun revokeAccess() {
        googleSignInManager.revokeAccess()
    }

    fun onItemClick(itemType: ProfileListItemType) {
        when (itemType) {
            ProfileListItemType.Settings,
            ProfileListItemType.About,
            ProfileListItemType.RateUs -> Unit
            ProfileListItemType.SignIn -> {
                val googleSignInClient = getGoogleSignInClient()
                viewModelScope.launch {
                    signInFlow.emit(googleSignInClient.signInIntent)
                }

            }
            ProfileListItemType.SignOut -> {
                signOut()
            }
        }
    }
}

