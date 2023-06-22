package com.ink1804.dev.undefinedapp.di

import com.ink1804.dev.common.network.CommonNetworkConfig
import com.ink1804.dev.undefinedapp.config.CommonNetworkConfigImpl
import org.koin.dsl.module

val KoinConfigModule = module {
    single<CommonNetworkConfig> { CommonNetworkConfigImpl() }
}
