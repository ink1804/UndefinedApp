package com.ink1804.dev.feature.profile.ui

import androidx.lifecycle.ViewModel
import com.ink1804.dev.feature.profile.navigation.ProfileCoordinator

class ProfileViewModel(
    coordinator: ProfileCoordinator
) : ViewModel(), ProfileCoordinator by coordinator {
}