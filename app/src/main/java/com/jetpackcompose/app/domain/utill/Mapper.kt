package com.jetpackcompose.app.domain.utill

interface Mapper<DTO, DomainModel> {
    fun mapToDomainModel(dto: DTO): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): DTO
}