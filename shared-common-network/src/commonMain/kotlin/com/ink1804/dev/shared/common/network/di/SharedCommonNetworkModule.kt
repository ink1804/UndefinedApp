package com.ink1804.dev.shared.common.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val COMMON_NETWORK_QUALIFIER = "common_network"

val SharedCommonNetworkModule = module {
    includes(
        KoinEngineNetworkModule,
        KoinInterceptorsModule
    )

    single(named(COMMON_NETWORK_QUALIFIER)) {
        provideHttpClient(
            httpEngine = get(),
        )
    }
}

fun provideHttpClient(
    httpEngine: HttpClientEngine
): HttpClient = HttpClient(httpEngine) {
    expectSuccess = true

    install(DefaultRequest) {
        //todo move to constants and mb provide via config
        header("Content-Type", "application/json")
    }
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

