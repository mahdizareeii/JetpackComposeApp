package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import javax.inject.Inject

interface SearchRecipesUseCase {
    suspend fun execute(page: Int, query: String, token: String): List<Recipe>
}

class SearchRecipesUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) : SearchRecipesUseCase {
    override suspend fun execute(page: Int, query: String, token: String): List<Recipe> {
        return mapper.toDomainList(repository.search(token, page, query).results ?: listOf())
    }
}