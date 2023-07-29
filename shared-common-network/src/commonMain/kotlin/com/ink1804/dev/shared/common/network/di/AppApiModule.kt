package com.ink1804.dev.shared.common.network.di

import com.ink1804.dev.shared.common.network.TestApi
import com.ink1804.dev.shared.common.network.TestApiImpl
import com.ink1804.dev.shared.common.network.data.TestRepositoryImpl
import com.ink1804.dev.shared.common.network.domain.repository.TestRepository
import com.ink1804.dev.shared.common.network.domain.usecase.TestInteractor
import com.ink1804.dev.shared.common.network.domain.usecase.TestUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import org.koin.core.qualifier.named
import org.koin.dsl.module

val KoinSharedTestApiModule = module {
    includes(SharedCommonNetworkModule)

    single<TestApi> { TestApiImpl(ktor = get()) }
    single<TestRepository> {
        TestRepositoryImpl(
            api = get()
        )
    }

    factory<TestUseCase> { TestInteractor(repository = get()) }

    factory {
        provideKtor(
            client = get(named(COMMON_NETWORK_QUALIFIER)),
        )
    }
}

fun provideKtor(
    client: HttpClient,
): HttpClient {
    return client.config {
        defaultRequest {
            url("https://ktor.io/")
        }
    }
}

