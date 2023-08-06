package com.ink1804.dev.feature.settings.ui

import com.ink1804.dev.common.ui.base.FlowViewModel
import com.ink1804.dev.feature.settings.navigation.SettingsCoordinator

class SettingsViewModel(
    coordinator: SettingsCoordinator
) : FlowViewModel(), SettingsCoordinator by coordinator {
}