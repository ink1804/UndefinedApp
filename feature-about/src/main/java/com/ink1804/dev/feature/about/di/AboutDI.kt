package com.ink1804.dev.feature.about.di

import com.ink1804.dev.feature.about.ui.AboutViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val KoinFeatureAboutModule = module {
    viewModelOf(::AboutViewModel)
}