package com.ink1804.dev.common.network.domain.entity

data class GptCompletionsResponseEntity(
    val id: String,
    val choices: List<GptChoiceEntity>
)

data class GptChoiceEntity(
    val message: GptMessageEntity,
    val finishReason: String,
)

data class GptCompletionRequestEntity(
    val model: GptModelEntity,
    val messages: List<GptMessageEntity>
)

data class GptMessageEntity(
    val role: GptRoleEntity,
    val content: String
)

enum class GptModelEntity(val value: String) {
    Gpt35Turbo("gpt-3.5-turbo"),
    Gpt4("gpt-4"),
    Unknown("unknown");

    companion object {
        fun getValueOf(value: String): GptModelEntity =
            GptModelEntity.values().find { it.value == value } ?: Unknown
    }
}

enum class GptRoleEntity(val value: String) {
    User("user"),
    System("system"),
    Assistant("assistant"),
    Unknown("unknown");

    companion object {
        fun getValueOf(value: String): GptRoleEntity =
            GptRoleEntity.values().find { it.value == value } ?: Unknown
    }

}