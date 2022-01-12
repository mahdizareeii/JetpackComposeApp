package com.jetpackcompose.domain.mapper

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.network.model.RecipeDto
import com.jetpackcompose.core.util.mapper.Mapper

class RecipeDtoMapper : Mapper<RecipeDto, Recipe> {
    override fun mapToDomainModel(dto: RecipeDto?): Recipe {
        return Recipe(
            id = dto?.primaryKey,
            title = dto?.title,
            publisher = dto?.publisher,
            featuredImage = dto?.featuredImage,
            rating = dto?.rating,
            sourceUrl = dto?.sourceUrl,
            description = dto?.description,
            cookingInstructions = dto?.cookingInstructions,
            ingredients = dto?.ingredients,
            dateAdded = dto?.dateAdded,
            dateUpdated = dto?.dateUpdated
        )
    }

    override fun mapToDTO(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            primaryKey = domainModel.id,
            title = domainModel.title,
            publisher = domainModel.publisher,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated
        )
    }

    fun toDomainList(list: List<RecipeDto>): List<Recipe> {
        return list.map { mapToDomainModel(it) }
    }

    fun toDtoList(list: List<Recipe>): List<RecipeDto> {
        return list.map { mapToDTO(it) }
    }
}