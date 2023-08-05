package com.ink1804.dev.feature.profile.di

import com.ink1804.dev.feature.profile.data.GoogleSignInManager
import com.ink1804.dev.feature.profile.data.GoogleSignInManagerImpl
import com.ink1804.dev.feature.profile.ui.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val KoinFeatureProfileModule = module {
    viewModelOf(::ProfileViewModel)

    single<GoogleSignInManager> {
        GoogleSignInManagerImpl(activityContext = get())
    }
}
