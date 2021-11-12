package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import com.jetpackcompose.domain.util.DataState
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    suspend operator fun invoke(id: Int): DataState<Recipe> {
        return when (val result = repository.getRecipeById(id)) {
            is DataState.Success -> DataState.Success(mapper.mapToDomainModel(result.data))
            is DataState.Error -> result
        }
    }
}