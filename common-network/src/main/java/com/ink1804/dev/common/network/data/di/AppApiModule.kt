package com.ink1804.dev.common.network.data.di

import org.koin.dsl.module

val KoinAppApiModule = module {
    includes(KoinCommonNetworkModule)
}

