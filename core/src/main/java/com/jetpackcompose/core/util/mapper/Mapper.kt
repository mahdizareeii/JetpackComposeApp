package com.jetpackcompose.core.util.mapper

interface Mapper<DTO, DomainModel> {
    fun mapToDomainModel(dto: DTO?): DomainModel
    fun mapToDTO(domainModel: DomainModel): DTO
}