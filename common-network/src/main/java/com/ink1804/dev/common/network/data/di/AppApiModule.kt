package com.ink1804.dev.common.network.data.di

import dagger.Module
import org.koin.dsl.module

val KoinAppApiModule = module {
    includes(KoinCommonNetworkModule)
}

@Module(
    includes = [
        CommonNetworkModule::class
    ]
)
object AppApiModule {

}