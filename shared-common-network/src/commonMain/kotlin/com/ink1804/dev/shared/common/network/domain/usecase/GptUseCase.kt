package com.ink1804.dev.shared.common.network.domain.usecase

import com.ink1804.dev.shared.common.network.domain.repository.TestRepository

interface TestUseCase {
    suspend fun docs(): String
    fun getStr(): String
}

internal class TestInteractor(
    private val repository: TestRepository
) : TestUseCase {
    override suspend fun docs(): String {
        return repository.docs("")
    }

    override fun getStr(): String {
        return "hello there"
    }
}