package com.ink1804.dev.common.network.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class GptCompletionsResponseData(
    @SerialName("id")
    val id: String,
    @SerialName("choices")
    val choices: List<GptChoiceData>
)

@Serializable
internal data class GptChoiceData(
    @SerialName("message")
    val message: GptMessageData,
    @SerialName("finish_reason")
    val finishReason: String,
)

@Serializable
internal data class GptCompletionRequestData(
    @SerialName("model")
    val model: String,
    @SerialName("messages")
    val messages: List<GptMessageData>
)

@Serializable
internal data class GptMessageData(
    @SerialName("role")
    val role: String,
    @SerialName("content")
    val content: String
)
