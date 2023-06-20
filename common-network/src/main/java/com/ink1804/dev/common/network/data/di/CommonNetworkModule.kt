package com.ink1804.dev.common.network.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ink1804.dev.common.network.data.interceptor.HeaderInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter

@Module
object CommonNetworkModule {

    @Provides
    fun provideOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        headerInterceptor: HeaderInterceptor? = null
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .also {
                headerInterceptor?.let(it::addNetworkInterceptor)
            }
            .build()


    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().also { logger ->
            logger.level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context).build()

    @Provides
    fun provideJsonConverter(): Converter.Factory =
        JSON.asConverterFactory(DEFAULT_MEDIA_TYPE.toMediaType())


    private val JSON = Json { ignoreUnknownKeys = true }
    private const val DEFAULT_MEDIA_TYPE = "application/json"
}