package com.ink1804.dev.feature.about.ui

import com.ink1804.dev.common.ui.base.FlowViewModel
import com.ink1804.dev.feature.about.navigation.AboutCoordinator

class AboutViewModel(
    coordinator: AboutCoordinator
) : FlowViewModel(), AboutCoordinator by coordinator {
}