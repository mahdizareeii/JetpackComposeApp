package com.jetpackcompose.domain.network.api

import com.jetpackcompose.domain.network.model.BaseRecipeSearchDto
import com.jetpackcompose.domain.network.model.RecipeDto
import com.jetpackcompose.domain.util.model.NetworkDataState
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {

    @GET("search")
    suspend fun search(
        @Query("page") page: Int,
        @Query("query") query: String
    ): NetworkDataState<BaseRecipeSearchDto<List<RecipeDto>>>

    @GET("get")
    suspend fun getRecipeById(
        @Query("id") id: Int
    ): NetworkDataState<RecipeDto>

}