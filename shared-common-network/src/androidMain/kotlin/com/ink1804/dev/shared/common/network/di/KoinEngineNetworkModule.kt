package com.ink1804.dev.shared.common.network.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

actual val KoinEngineNetworkModule = module {
    single() {
        provideHttpEngine(
            loggingInterceptor = get(),
            chuckerInterceptor = get(),
//            headerInterceptor = get()
        )
    }
}

fun provideHttpEngine(
    loggingInterceptor: HttpLoggingInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
//    headerInterceptor: HeaderInterceptor? = null
): HttpClientEngine = OkHttp.create {
    addInterceptor(loggingInterceptor)
    addInterceptor(chuckerInterceptor) //todo provide via interface cuz its an android dependency
//    headerInterceptor?.let { addNetworkInterceptor(it) }
}