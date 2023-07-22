package com.ink1804.dev.shared.common.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

internal interface TestApi {
    suspend fun docs(request: Any): String
}

internal class TestApiImpl(
    private val ktor: HttpClient
) : TestApi {
    override suspend fun docs(request: Any): String {
        return ktor.get("docs").bodyAsText()
    }
}





