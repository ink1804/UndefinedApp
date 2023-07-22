package com.ink1804.dev.shared.common.network.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val KoinInterceptorsModule = module {
    single {
        HttpLoggingInterceptor().also { logger ->
            logger.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        ChuckerInterceptor.Builder(androidContext()).build()
    }
}