package com.ink1804.dev.undefinedapp.di

import com.ink1804.dev.common.network.CommonNetworkConfig
import com.ink1804.dev.undefinedapp.config.CommonNetworkConfigImpl
import dagger.Binds
import dagger.Module
import org.koin.dsl.module

val KoinConfigModule = module{
    single<CommonNetworkConfig> { CommonNetworkConfigImpl() }
}

@Module
interface ConfigsModule {
    @Binds
    fun commonNetworkConfig(config: CommonNetworkConfigImpl): CommonNetworkConfig
}