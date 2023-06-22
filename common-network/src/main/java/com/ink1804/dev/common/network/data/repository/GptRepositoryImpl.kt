package com.ink1804.dev.common.network.data.repository

import com.ink1804.dev.common.network.data.api.GptApi
import com.ink1804.dev.common.network.data.mapper.GptCompletionsRequestEntityDataMapper
import com.ink1804.dev.common.network.data.mapper.GptCompletionsResponseDataEntityMapper
import com.ink1804.dev.common.network.domain.entity.GptCompletionRequestEntity
import com.ink1804.dev.common.network.domain.entity.GptCompletionsResponseEntity
import com.ink1804.dev.common.network.domain.repository.GptRepository

internal class GptRepositoryImpl(
    private val gptApi: GptApi,
    private val completionsRequestMapper: GptCompletionsRequestEntityDataMapper,
    private val completionsResponseMapper: GptCompletionsResponseDataEntityMapper
) : GptRepository, GptApi by gptApi {

    override suspend fun completions(request: GptCompletionRequestEntity): GptCompletionsResponseEntity {
        return gptApi.completions(completionsRequestMapper.mapTo(request))
            .let(completionsResponseMapper::mapTo)
    }
}