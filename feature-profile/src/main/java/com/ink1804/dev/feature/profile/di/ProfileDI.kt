package com.ink1804.dev.feature.profile.di

import com.ink1804.dev.feature.profile.data.SignInRepository
import com.ink1804.dev.feature.profile.data.SignInRepositoryImpl
import com.ink1804.dev.feature.profile.ui.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val KoinFeatureProfileModule = module {
    viewModelOf(::ProfileViewModel)

    single<SignInRepository> {
        SignInRepositoryImpl(activityContext = get())
    }
}
