package com.ink1804.dev.common.network

//todo move to one of common modules
interface DataEntityMapper<Data, Entity> {
    fun mapDataEntity(from: Data): Entity
    fun mapEntityData(from: Entity): Data
}

interface Mapper<From, To> {
    fun mapTo(from: From): To
}