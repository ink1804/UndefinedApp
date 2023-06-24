package com.ink1804.dev.undefinedapp.di

import androidx.navigation.NavHostController
import com.ink1804.dev.common.ui.NavControllerProvider
import com.ink1804.dev.common.ui.NavHostProviderIsNotSet
import com.ink1804.dev.feature.main.navigation.MainCoordinator
import com.ink1804.dev.feature.splash.navigation.SplashCoordinator
import com.ink1804.dev.undefinedapp.navigation.MainCoordinatorImpl
import com.ink1804.dev.undefinedapp.navigation.SplashCoordinatorImpl
import org.koin.dsl.module

val KoinNavigationModule = module {
    single<NavControllerProvider> { NavControllerProviderImpl() }

    factory<SplashCoordinator> { SplashCoordinatorImpl(get()) }
    factory<MainCoordinator> { MainCoordinatorImpl(get()) }
}

class NavControllerProviderImpl : NavControllerProvider {
    private var navHostController: NavHostController? = null

    override fun provide(navHostController: NavHostController) {
        this.navHostController = navHostController
    }

    override fun get() = navHostController ?: throw NavHostProviderIsNotSet()
}




