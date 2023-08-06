package com.ink1804.dev.undefinedapp.navigation

import com.ink1804.dev.common.ui.NavControllerProvider
import com.ink1804.dev.feature.profile.navigation.ProfileCoordinator

class ProfileCoordinatorImpl(
    private val navControllerProvider: NavControllerProvider
) : ProfileCoordinator {

    override fun openSettingsScreen(){
        navControllerProvider.navigate(AppDestination.Settings.screenName)
    }

    override fun openAboutScreen() {
        navControllerProvider.navigate(AppDestination.About.screenName)
    }
}