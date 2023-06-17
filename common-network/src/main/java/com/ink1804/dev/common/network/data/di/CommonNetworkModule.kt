package com.ink1804.dev.common.network.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ink1804.dev.common.network.data.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

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

}