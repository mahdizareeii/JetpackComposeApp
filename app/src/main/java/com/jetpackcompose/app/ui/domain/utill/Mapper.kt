package com.jetpackcompose.app.ui.domain.utill

interface Mapper<DTO, DomainModel> {
    fun mapToDomainModel(dto: DTO): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): DTO
}