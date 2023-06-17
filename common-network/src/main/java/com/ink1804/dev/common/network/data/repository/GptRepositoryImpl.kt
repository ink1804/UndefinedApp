package com.ink1804.dev.common.network.data.repository

import com.ink1804.dev.common.network.data.api.GptApi
import com.ink1804.dev.common.network.data.mapper.GptCompletionsRequestEntityDataMapper
import com.ink1804.dev.common.network.data.mapper.GptCompletionsResponseDataEntityMapper
import com.ink1804.dev.common.network.domain.repository.GptRepository
import com.ink1804.dev.common.network.domain.entity.GptCompletionRequestEntity
import com.ink1804.dev.common.network.domain.entity.GptCompletionsResponseEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GptRepositoryImpl @Inject constructor(
    private val gptApi: GptApi,
    private val completionsRequestMapper: GptCompletionsRequestEntityDataMapper,
    private val completionsResponseMapper: GptCompletionsResponseDataEntityMapper
) : GptRepository, GptApi by gptApi {

    override suspend fun completions(request: GptCompletionRequestEntity): GptCompletionsResponseEntity {
        return gptApi.completions(completionsRequestMapper.mapTo(request))
            .let(completionsResponseMapper::mapTo)
    }
}