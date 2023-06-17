package com.ink1804.dev.common.network.data.di

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

    private const val API_KEY = "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"

    @Provides
    @Named(GPT_DAGGER_NAME)
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    internal fun provideHeaderInterceptor(): HeaderInterceptor = GptHeaderInterceptor()

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