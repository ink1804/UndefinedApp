package com.ink1804.dev.feature.map.ui

import androidx.lifecycle.ViewModel
import com.ink1804.dev.feature.map.navigation.MapCoordinator

class MapViewModel(
    coordinator: MapCoordinator
) : ViewModel(), MapCoordinator by coordinator {
}