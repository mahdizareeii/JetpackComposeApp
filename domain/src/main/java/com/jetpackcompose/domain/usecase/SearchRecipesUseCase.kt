package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import com.jetpackcompose.domain.utill.network.calladapter.Result
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    suspend operator fun invoke(page: Int, query: String): Result<List<Recipe>> {
        return when (val result = repository.search(page, query)) {
            is Result.Success -> Result.Success(
                mapper.toDomainList(result.data.results ?: listOf())
            )
            is Result.Error -> result
        }
    }
}