package com.ink1804.dev.common.network.data.api

import com.ink1804.dev.common.network.data.entity.GptCompletionRequestData
import com.ink1804.dev.common.network.data.entity.GptCompletionsResponseData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

//Api reference https://platform.openai.com/docs/api-reference/chat/create
//Ktor migration https://apiumhub.com/tech-blog-barcelona/migrating-retrofit-to-ktor/
internal interface GptApi {
    suspend fun completions(request: GptCompletionRequestData): GptCompletionsResponseData
}

internal class GptApiImpl(
    private val ktor: HttpClient
) : GptApi {
    override suspend fun completions(request: GptCompletionRequestData): GptCompletionsResponseData {
        return ktor.post("chat/completions") { setBody(request) }
            .body()
    }
}





