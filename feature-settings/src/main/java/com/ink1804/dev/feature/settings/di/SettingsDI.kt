package com.ink1804.dev.feature.settings.di

import com.ink1804.dev.feature.settings.ui.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val KoinFeatureSettingsModule = module {
    viewModelOf(::SettingsViewModel)
}