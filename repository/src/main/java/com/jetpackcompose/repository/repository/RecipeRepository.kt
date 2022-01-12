package com.jetpackcompose.repository.repository

import com.jetpackcompose.network.api.RecipeApiService
import com.jetpackcompose.network.model.BaseRecipeSearchDto
import com.jetpackcompose.network.model.RecipeDto
import com.jetpackcompose.core.model.NetworkDataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface RecipeRepository {
    suspend fun search(
        page: Int,
        query: String
    ): NetworkDataState<BaseRecipeSearchDto<List<RecipeDto>>>

    suspend fun getRecipeById(id: Int): NetworkDataState<RecipeDto>
}

class RecipeRepositoryImpl(
    private val apiService: RecipeApiService,
    private val dispatcher: CoroutineDispatcher
) : RecipeRepository {
    override suspend fun search(
        page: Int,
        query: String
    ): NetworkDataState<BaseRecipeSearchDto<List<RecipeDto>>> {
        return withContext(dispatcher) {
            apiService.search(page, query)
        }
    }

    override suspend fun getRecipeById(id: Int): NetworkDataState<RecipeDto> {
        return withContext(dispatcher) {
            apiService.getRecipeById(id)
        }
    }
}