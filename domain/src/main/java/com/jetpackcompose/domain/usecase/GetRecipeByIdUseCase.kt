package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import javax.inject.Inject

interface GetRecipeByIdUseCase {
    suspend fun execute(id: Int): Recipe
}

class GetRecipeByIdUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) : GetRecipeByIdUseCase {
    override suspend fun execute(id: Int): Recipe {
        return mapper.mapToDomainModel(repository.getRecipeById("todo", id))
    }
}