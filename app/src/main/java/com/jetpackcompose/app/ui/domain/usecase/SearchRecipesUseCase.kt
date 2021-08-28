package com.jetpackcompose.app.ui.domain.usecase

import com.jetpackcompose.app.ui.domain.model.Recipe
import com.jetpackcompose.app.ui.network.mapper.RecipeDtoMapper
import com.jetpackcompose.app.ui.network.repository.RecipeRepository

interface SearchRecipesUseCase {
    suspend fun execute(page: Int, query: String): List<Recipe>
}

class SearchRecipesUseCaseImpl(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) : SearchRecipesUseCase {
    override suspend fun execute(page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(repository.search("todo", page, query).results ?: listOf())
    }
}