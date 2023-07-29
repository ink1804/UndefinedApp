package com.ink1804.dev.shared.common.network.data

import com.ink1804.dev.shared.common.network.TestApi
import com.ink1804.dev.shared.common.network.domain.repository.TestRepository

internal class TestRepositoryImpl(
    private val api: TestApi,
) : TestRepository {

    override suspend fun docs(request: Any): String {
        return api.docs("")
    }
}