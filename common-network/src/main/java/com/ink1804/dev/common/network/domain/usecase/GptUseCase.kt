package com.ink1804.dev.common.network.domain.usecase

import com.ink1804.dev.common.network.domain.entity.GptCompletionRequestEntity
import com.ink1804.dev.common.network.domain.entity.GptMessageEntity
import com.ink1804.dev.common.network.domain.entity.GptModelEntity.Gpt35Turbo
import com.ink1804.dev.common.network.domain.entity.GptRoleEntity.User
import com.ink1804.dev.common.network.domain.repository.GptRepository

interface GptUseCase {
    suspend fun completions()
}

internal class GptInteractor(
    private val gptRepository: GptRepository
) : GptUseCase {
    override suspend fun completions() {
        gptRepository.completions(
            GptCompletionRequestEntity(
                model = Gpt35Turbo,
                messages = listOf(
                    GptMessageEntity(
                        User,
                        "what is chat gpt"
                    )
                )
            )
        )
    }
}