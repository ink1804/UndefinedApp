package com.ink1804.dev.feature.main.ui

import androidx.lifecycle.ViewModel
import com.ink1804.dev.feature.main.navigation.MainCoordinator

class MainViewModel(
    coordinator: MainCoordinator
) : ViewModel(), MainCoordinator by coordinator {
}