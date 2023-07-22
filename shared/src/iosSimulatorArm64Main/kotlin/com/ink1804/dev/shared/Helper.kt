package com.ink1804.dev.shared

import com.ink1804.dev.shared.common.network.di.KoinSharedTestApiModule
import com.ink1804.dev.shared.common.network.domain.usecase.TestUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class ApiHelper : KoinComponent {
    val testUseCase: TestUseCase by inject()
    suspend fun docs(): String = testUseCase.docs()

}

fun initKoin() {
    startKoin {
        modules(KoinSharedTestApiModule)
    }
}