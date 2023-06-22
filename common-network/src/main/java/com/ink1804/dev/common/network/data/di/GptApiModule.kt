package com.ink1804.dev.common.network.data.di

import com.ink1804.dev.common.network.CommonNetworkConfig
import com.ink1804.dev.common.network.data.api.GptApi
import com.ink1804.dev.common.network.data.api.GptApiImpl
import com.ink1804.dev.common.network.data.interceptor.GptHeaderInterceptor
import com.ink1804.dev.common.network.data.interceptor.HeaderInterceptor
import com.ink1804.dev.common.network.data.mapper.KoinGptMappersModule
import com.ink1804.dev.common.network.data.repository.GptRepositoryImpl
import com.ink1804.dev.common.network.domain.repository.GptRepository
import com.ink1804.dev.common.network.domain.usecase.GptInteractor
import com.ink1804.dev.common.network.domain.usecase.GptUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module


val KoinGptApiModule = module {
    includes(
        KoinCommonNetworkModule,
        KoinGptMappersModule
    )

    single<GptApi> { GptApiImpl(ktor = get()) }
    single<GptRepository> {
        GptRepositoryImpl(
            gptApi = get(),
            completionsRequestMapper = get(),
            completionsResponseMapper = get()
        )
    }
    single<GptUseCase> { GptInteractor(gptRepository = get()) }

    single<HeaderInterceptor> {
        GptHeaderInterceptor(commonNetworkConfig = get())
    }

    single {
        provideKtor(
            client = get(StringQualifier(COMMON_NETWORK_QUALIFIER)),
            config = get())
    }
}


fun provideKtor(
    client: HttpClient,
    config: CommonNetworkConfig
): HttpClient {
    return client.config {
        defaultRequest {
            url(config.gptApiBaseUrl)
        }
    }
}

