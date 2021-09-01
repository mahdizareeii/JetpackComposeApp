package com.jetpackcompose.app.network.api

import com.jetpackcompose.app.network.model.BaseRecipeSearchDto
import com.jetpackcompose.app.network.model.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeApiService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): BaseRecipeSearchDto<List<RecipeDto>>

    @GET("get")
    suspend fun getRecipeById(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto

}