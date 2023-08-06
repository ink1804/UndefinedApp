package com.ink1804.dev.common.ui

import androidx.navigation.NavHostController

interface NavControllerProvider {
    fun provide(navHostController: NavHostController)

    @Throws(NavHostProviderIsNotSet::class)
    fun get(): NavHostController

    @Throws(NavHostProviderIsNotSet::class)
    fun navigate(destination: String){
        get().navigate(destination)
    }
}

class NavHostProviderIsNotSet : Throwable()