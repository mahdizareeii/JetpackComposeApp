package com.jetpackcompose.domain.network.api

import com.jetpackcompose.domain.network.model.BaseRecipeSearchDto
import com.jetpackcompose.domain.network.model.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeApiService {

    @GET("search")
    suspend fun search(
        @Query("page") page: Int,
        @Query("query") query: String
    ): BaseRecipeSearchDto<List<RecipeDto>>

    @GET("get")
    suspend fun getRecipeById(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto

}