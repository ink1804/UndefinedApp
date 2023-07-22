package com.ink1804.dev.shared.common.network.domain.repository

interface TestRepository {
    suspend fun docs(request: Any): String
}

