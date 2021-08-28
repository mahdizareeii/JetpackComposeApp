package com.jetpackcompose.app.ui.domain.usecase

import com.jetpackcompose.app.ui.domain.model.Recipe
import com.jetpackcompose.app.ui.network.mapper.RecipeDtoMapper
import com.jetpackcompose.app.ui.network.repository.RecipeRepository

interface GetRecipeByIdUseCase {
    suspend fun execute(id: Int): Recipe
}

class GetRecipeByIdUseCaseImpl(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) : GetRecipeByIdUseCase {
    override suspend fun execute(id: Int): Recipe {
        return mapper.mapToDomainModel(repository.getRecipeById("todo", id))
    }
}