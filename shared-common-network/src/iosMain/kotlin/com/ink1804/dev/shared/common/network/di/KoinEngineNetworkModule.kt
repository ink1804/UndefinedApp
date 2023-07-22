package com.ink1804.dev.shared.common.network.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val KoinEngineNetworkModule = module {
    single {
        provideHttpEngine()
    }
}

fun provideHttpEngine(): HttpClientEngine = Darwin.create {
    //configure intercepter here
}