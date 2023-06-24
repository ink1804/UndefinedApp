package com.ink1804.dev.feature.splash.di

import com.ink1804.dev.feature.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val KoinFeatureSplashModule = module {
    viewModelOf(::SplashViewModel)
}