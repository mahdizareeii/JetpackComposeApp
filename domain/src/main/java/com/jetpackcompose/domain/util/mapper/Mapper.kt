package com.jetpackcompose.domain.util.mapper

interface Mapper<DTO, DomainModel> {
    fun mapToDomainModel(dto: DTO?): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): DTO
}