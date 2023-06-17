package com.ink1804.dev.common.network.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class GptCompletionsResponseData(
    @Json(name = "id")
    val id: String,
    @Json(name = "choices")
    val choices: List<GptChoiceData>
)

@JsonClass(generateAdapter = true)
internal data class GptChoiceData(
    @Json(name = "message")
    val message: GptMessageData,
    @Json(name = "finish_reason")
    val finishReason: String,
)

@JsonClass(generateAdapter = true)
internal data class GptCompletionRequestData(
    @Json(name = "model")
    val model: String,
    @Json(name = "messages")
    val messages: List<GptMessageData>
)

@JsonClass(generateAdapter = true)
internal data class GptMessageData(
    @Json(name = "role")
    val role: String,
    @Json(name = "content")
    val content: String
)
