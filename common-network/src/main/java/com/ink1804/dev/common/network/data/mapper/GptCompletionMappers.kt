package com.ink1804.dev.common.network.data.mapper

import com.ink1804.dev.common.network.data.entity.GptChoiceData
import com.ink1804.dev.common.network.data.entity.GptCompletionRequestData
import com.ink1804.dev.common.network.data.entity.GptCompletionsResponseData
import com.ink1804.dev.common.network.data.entity.GptMessageData
import com.ink1804.dev.common.network.DataEntityMapper
import com.ink1804.dev.common.network.Mapper
import com.ink1804.dev.common.network.domain.entity.GptChoiceEntity
import com.ink1804.dev.common.network.domain.entity.GptCompletionRequestEntity
import com.ink1804.dev.common.network.domain.entity.GptCompletionsResponseEntity
import com.ink1804.dev.common.network.domain.entity.GptMessageEntity
import com.ink1804.dev.common.network.domain.entity.GptRoleEntity
import javax.inject.Inject

internal class GptCompletionsRequestEntityDataMapper @Inject constructor(
    private val messageMapper: GptMessageMapper
) : Mapper<GptCompletionRequestEntity, GptCompletionRequestData> {
    override fun mapTo(from: GptCompletionRequestEntity): GptCompletionRequestData =
        GptCompletionRequestData(
            model = from.model.value,
            messages = from.messages.map(messageMapper::mapEntityData)
        )
}

internal class GptCompletionsResponseDataEntityMapper @Inject constructor(
    private val choiceMapper: GptChoiceDataEntityMapper
) : Mapper<GptCompletionsResponseData, GptCompletionsResponseEntity> {
    override fun mapTo(from: GptCompletionsResponseData): GptCompletionsResponseEntity =
        GptCompletionsResponseEntity(
            id = from.id,
            choices = from.choices.map(choiceMapper::mapTo)
        )
}

internal class GptMessageMapper @Inject constructor() : DataEntityMapper<GptMessageData, GptMessageEntity> {
    override fun mapDataEntity(from: GptMessageData): GptMessageEntity =
        GptMessageEntity(
            role = GptRoleEntity.getValueOf(from.role),
            content = from.content
        )


    override fun mapEntityData(from: GptMessageEntity): GptMessageData =
        GptMessageData(
            role = from.role.value,
            content = from.content
        )
}

internal class GptChoiceDataEntityMapper @Inject constructor(
    private val messageMapper: GptMessageMapper
) : Mapper<GptChoiceData, GptChoiceEntity> {
    override fun mapTo(from: GptChoiceData): GptChoiceEntity =
        GptChoiceEntity(
            message = messageMapper.mapDataEntity(from.message),
            finishReason = from.finishReason
        )
}

