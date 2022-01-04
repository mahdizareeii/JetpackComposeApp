package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import com.jetpackcompose.domain.util.model.NetworkDataState
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    suspend operator fun invoke(page: Int, query: String): NetworkDataState<List<Recipe>> {
        return when (val result = repository.search(page, query)) {
            is NetworkDataState.Success -> NetworkDataState.Success(
                mapper.toDomainList(result.data.results ?: listOf())
            )
            is NetworkDataState.Error -> NetworkDataState.Error(result.networkStatus)
        }
    }
}