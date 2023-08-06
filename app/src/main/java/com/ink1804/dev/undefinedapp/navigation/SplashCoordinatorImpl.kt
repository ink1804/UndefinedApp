package com.ink1804.dev.undefinedapp.navigation

import com.ink1804.dev.common.ui.NavControllerProvider
import com.ink1804.dev.feature.splash.navigation.SplashCoordinator

class SplashCoordinatorImpl(
    private val navControllerProvider: NavControllerProvider
) : SplashCoordinator {
    override fun navigateMainScreen() {
        navControllerProvider.navigate(AppDestination.Main.screenName)
    }
}