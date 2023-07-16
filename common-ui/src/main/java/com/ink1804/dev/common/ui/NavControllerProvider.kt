package com.ink1804.dev.common.ui

import androidx.navigation.NavHostController

interface NavControllerProvider {
    fun provide(navHostController: NavHostController)

    @Throws(NavHostProviderIsNotSet::class)
    fun get(): NavHostController
}

class NavHostProviderIsNotSet : Throwable()