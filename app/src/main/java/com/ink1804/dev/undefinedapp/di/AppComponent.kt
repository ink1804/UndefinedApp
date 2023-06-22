package com.ink1804.dev.undefinedapp.di

import com.ink1804.dev.common.network.data.di.KoinAppApiModule
import com.ink1804.dev.common.network.data.di.KoinCommonNetworkModule
import com.ink1804.dev.common.network.data.di.KoinGptApiModule
import org.koin.dsl.module


val KoinAppComponent = module {
    includes(
        KoinConfigModule,
        KoinAppApiModule,
        KoinGptApiModule,
        KoinCommonNetworkModule,
    )
}
