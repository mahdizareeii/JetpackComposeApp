package com.jetpackcompose.domain.usecase

import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import com.jetpackcompose.domain.util.model.NetworkDataState
import com.jetpackcompose.domain.util.model.UiDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    private val mapper: RecipeDtoMapper
) {
    operator fun invoke(page: Int, query: String): Flow<UiDataState<List<Recipe>>> = flow {
        emit(UiDataState.Loading(true))
        emit(
            when (val result = repository.search(page, query)) {
                is NetworkDataState.Success -> UiDataState.Success(
                    mapper.toDomainList(result.data.results ?: listOf())
                )
                is NetworkDataState.Error -> UiDataState.Error(result.networkStatus)
            }
        )
        emit(UiDataState.Loading(false))
    }
}