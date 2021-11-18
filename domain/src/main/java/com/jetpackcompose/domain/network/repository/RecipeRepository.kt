package com.jetpackcompose.domain.network.repository

import com.jetpackcompose.domain.network.api.RecipeApiService
import com.jetpackcompose.domain.network.model.BaseRecipeSearchDto
import com.jetpackcompose.domain.network.model.RecipeDto
import com.jetpackcompose.domain.util.model.NetworkDataState

interface RecipeRepository {

    suspend fun search(
        page: Int,
        query: String
    ): NetworkDataState<BaseRecipeSearchDto<List<RecipeDto>>>

    suspend fun getRecipeById(id: Int): NetworkDataState<RecipeDto>

}

class RecipeRepositoryImpl(
    private val apiService: RecipeApiService
) : RecipeRepository {
    override suspend fun search(
        page: Int,
        query: String
    ): NetworkDataState<BaseRecipeSearchDto<List<RecipeDto>>> {
        return apiService.search(page, query)
    }

    override suspend fun getRecipeById(id: Int): NetworkDataState<RecipeDto> {
        return apiService.getRecipeById(id)
    }
}