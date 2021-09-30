package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    suspend operator fun invoke(page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(repository.search(page, query).results ?: listOf())
    }
}