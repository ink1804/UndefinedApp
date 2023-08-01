package com.ink1804.dev.feature.profile.ui

import androidx.lifecycle.ViewModel
import com.ink1804.dev.feature.profile.data.SignInRepository
import com.ink1804.dev.feature.profile.navigation.ProfileCoordinator

class ProfileViewModel(
    coordinator: ProfileCoordinator,
    private val signInRepository: SignInRepository
) : ViewModel(),
    ProfileCoordinator by coordinator,
    SignInRepository by signInRepository{

}