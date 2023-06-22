package com.ink1804.dev.common.network.data.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ink1804.dev.common.network.data.interceptor.HeaderInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val COMMON_NETWORK_QUALIFIER = "common_network"

val KoinCommonNetworkModule = module {
    single(named(COMMON_NETWORK_QUALIFIER)) {
        provideHttpClient(
            loggingInterceptor = get(),
            chuckerInterceptor = get(),
            headerInterceptor = get()
        )
    }

    single {
        HttpLoggingInterceptor().also { logger ->
            logger.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        ChuckerInterceptor.Builder(androidContext()).build()
    }
}

fun provideHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
    headerInterceptor: HeaderInterceptor? = null
): HttpClient {
    return HttpClient(OkHttp) {
        expectSuccess = true

        engine {
            addInterceptor(loggingInterceptor)
            addInterceptor(chuckerInterceptor) //todo provide via interface cuz its an android dependency
            headerInterceptor?.let { addNetworkInterceptor(it) }
        }

        install(DefaultRequest) {
            //todo move to constants and mb provide via config
            header("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
}