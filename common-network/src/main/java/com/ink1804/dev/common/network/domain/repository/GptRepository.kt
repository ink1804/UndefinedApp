package com.ink1804.dev.common.network.domain.repository

import com.ink1804.dev.common.network.domain.entity.GptCompletionRequestEntity
import com.ink1804.dev.common.network.domain.entity.GptCompletionsResponseEntity

interface GptRepository {
    suspend fun completions(request: GptCompletionRequestEntity): GptCompletionsResponseEntity
}

