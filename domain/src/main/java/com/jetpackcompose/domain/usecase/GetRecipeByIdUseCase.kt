package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    suspend operator fun invoke(id: Int): Recipe {
        return mapper.mapToDomainModel(repository.getRecipeById("todo", id))
    }
}