package com.ink1804.dev.common.network.data.di

import com.ink1804.dev.common.network.CommonNetworkConfig
import com.ink1804.dev.common.network.data.api.GptApi
import com.ink1804.dev.common.network.data.api.GptApiImpl
import com.ink1804.dev.common.network.data.di.CommonNetworkModule.COMMON_NETWORK_NAME
import com.ink1804.dev.common.network.data.di.GptApiModule.BindsModule
import com.ink1804.dev.common.network.data.interceptor.GptHeaderInterceptor
import com.ink1804.dev.common.network.data.interceptor.HeaderInterceptor
import com.ink1804.dev.common.network.data.repository.GptRepositoryImpl
import com.ink1804.dev.common.network.domain.repository.GptRepository
import com.ink1804.dev.common.network.domain.usecase.GptInteractor
import com.ink1804.dev.common.network.domain.usecase.GptUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import javax.inject.Named

@Module(
    includes = [
        BindsModule::class,
        CommonNetworkModule::class
    ]
)
object GptApiModule {

    private const val GPT_DAGGER_NAME = "gpt_api_dagger_name"

    @Provides
    fun provideKtor(
        @Named(COMMON_NETWORK_NAME) client: HttpClient,
        config: CommonNetworkConfig
    ): HttpClient {
        return client.config {
            defaultRequest {
                url(config.gptApiBaseUrl)
            }
        }
    }

    @Provides
    internal fun provideHeaderInterceptor(
        commonNetworkConfig: CommonNetworkConfig
    ): HeaderInterceptor = GptHeaderInterceptor(commonNetworkConfig)

    @Module
    internal interface BindsModule {
        @Binds
        fun gptApi(api: GptApiImpl): GptApi

        @Binds
        fun gptRepository(repository: GptRepositoryImpl): GptRepository

        @Binds
        fun gptUseCase(useCase: GptInteractor): GptUseCase
    }
}