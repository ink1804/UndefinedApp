package com.ink1804.dev.undefinedapp.di

import com.ink1804.dev.common.network.CommonNetworkConfig
import com.ink1804.dev.undefinedapp.config.CommonNetworkConfigImpl
import dagger.Binds
import dagger.Module

@Module
interface ConfigsModule {
    @Binds
    fun commonNetworkConfig(config: CommonNetworkConfigImpl): CommonNetworkConfig
}