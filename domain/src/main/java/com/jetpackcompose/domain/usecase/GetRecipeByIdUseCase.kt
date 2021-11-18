package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import com.jetpackcompose.domain.util.model.NetworkDataState
import com.jetpackcompose.domain.util.model.UiDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    suspend operator fun invoke(id: Int): Flow<UiDataState<Recipe>> = flow {
        emit(UiDataState.Loading(true))
        emit(
            when (val result = repository.getRecipeById(id)) {
                is NetworkDataState.Success -> UiDataState.Success(mapper.mapToDomainModel(result.data))
                is NetworkDataState.Error -> UiDataState.Error(result.networkStatus)
            }
        )
        emit(UiDataState.Loading(false))
    }
}