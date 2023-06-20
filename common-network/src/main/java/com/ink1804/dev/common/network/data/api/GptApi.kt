package com.ink1804.dev.common.network.data.api

import com.ink1804.dev.common.network.data.entity.GptCompletionRequestData
import com.ink1804.dev.common.network.data.entity.GptCompletionsResponseData
import retrofit2.http.Body
import retrofit2.http.POST

//Api reference https://platform.openai.com/docs/api-reference/chat/create
internal interface GptApi {

    @POST("chat/completions")
    suspend fun completions(
        @Body request: GptCompletionRequestData
    ): GptCompletionsResponseData

}





