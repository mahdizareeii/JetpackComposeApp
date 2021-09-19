package com.jetpackcompose.domain.network.repository

import com.jetpackcompose.domain.network.api.RecipeApiService
import com.jetpackcompose.domain.network.model.BaseRecipeSearchDto
import com.jetpackcompose.domain.network.model.RecipeDto

interface RecipeRepository {

    suspend fun search(
        token: String,
        page: Int,
        query: String
    ): BaseRecipeSearchDto<List<RecipeDto>>

    suspend fun getRecipeById(token: String, id: Int): RecipeDto

}

class RecipeRepositoryImpl(
    private val apiService: RecipeApiService
) : RecipeRepository {
    override suspend fun search(
        token: String,
        page: Int,
        query: String
    ): BaseRecipeSearchDto<List<RecipeDto>> {
        return apiService.search(token, page, query)
    }

    override suspend fun getRecipeById(token: String, id: Int): RecipeDto {
        return apiService.getRecipeById(token, id)
    }
}