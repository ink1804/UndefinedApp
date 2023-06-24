package com.ink1804.dev.feature.splash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ink1804.dev.feature.splash.navigation.SplashCoordinator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    coordinator: SplashCoordinator
) : ViewModel(), SplashCoordinator by coordinator {

    fun openMainScreen() {
        viewModelScope.launch {
            delay(DELAY)
            navigateMainScreen()
        }
    }

    private companion object {
        const val DELAY = 2000L
    }
}
