package com.ink1804.dev.common.network.data.di

import com.ink1804.dev.common.network.CommonNetworkConfig
import com.ink1804.dev.common.network.data.api.GptApi
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
import javax.inject.Named
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(
    includes = [
        BindsModule::class,
        CommonNetworkModule::class
    ]
)
object GptApiModule {

    private const val GPT_DAGGER_NAME = "gpt_api_dagger_name"

    @Provides
    @Named(GPT_DAGGER_NAME)
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        commonNetworkConfig: CommonNetworkConfig
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(commonNetworkConfig.gptApiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    internal fun provideHeaderInterceptor(
        commonNetworkConfig: CommonNetworkConfig
    ): HeaderInterceptor = GptHeaderInterceptor(commonNetworkConfig)

    @Provides
    internal fun provideApi(@Named(GPT_DAGGER_NAME) retrofit: Retrofit): GptApi =
        retrofit.create(GptApi::class.java)

    @Module
    internal interface BindsModule {
        @Binds
        fun gptRepository(repository: GptRepositoryImpl): GptRepository

        @Binds
        fun gptUseCase(useCase: GptInteractor): GptUseCase
    }
}