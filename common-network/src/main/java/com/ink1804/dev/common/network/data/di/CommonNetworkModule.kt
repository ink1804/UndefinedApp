package com.ink1804.dev.common.network.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ink1804.dev.common.network.data.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Named
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

@Module
object CommonNetworkModule {

    const val COMMON_NETWORK_NAME = "common_network"

    @Provides
    @Named(COMMON_NETWORK_NAME)
    fun provideHttps(
        loggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        headerInterceptor: HeaderInterceptor? = null
    ): HttpClient {
        return HttpClient(OkHttp) {
            expectSuccess = true

            engine {
                addInterceptor(loggingInterceptor)
                addInterceptor(chuckerInterceptor)
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

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().also { logger ->
            logger.level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context).build()

}