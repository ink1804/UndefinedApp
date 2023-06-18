package com.ink1804.dev.common.network.data.di

import com.ink1804.dev.common.network.CommonNetworkConfig
import com.ink1804.dev.common.network.data.api.AppApi
import dagger.Module
import dagger.Provides
import javax.inject.Named
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(
    includes = [
        CommonNetworkModule::class
    ]
)
object AppApiModule {

    private const val APP_API_DAGGER_NAME = "app_api_dagger_name"

    @Provides
    @Named(APP_API_DAGGER_NAME)
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        commonNetworkConfig: CommonNetworkConfig
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(commonNetworkConfig.appApiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Named(APP_API_DAGGER_NAME)
    fun provideApi(retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)
}