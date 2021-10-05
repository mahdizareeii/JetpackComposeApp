package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import com.jetpackcompose.domain.utill.network.calladapter.Result
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    suspend operator fun invoke(id: Int): Result<Recipe> {
        return when (val result = repository.getRecipeById(id)) {
            is Result.Success -> Result.Success(mapper.mapToDomainModel(result.data))
            is Result.Error -> result
        }
    }
}