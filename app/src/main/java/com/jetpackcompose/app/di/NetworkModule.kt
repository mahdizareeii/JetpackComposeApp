package com.jetpackcompose.app.di

import com.google.gson.GsonBuilder
import com.jetpackcompose.app.network.api.RecipeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *  you should give the scope to @InstallIn() to inject something based on this document
 *  https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideService(): RecipeApiService = Retrofit.Builder()
        .baseUrl("https://food2fork.ca/api/recipe/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(RecipeApiService::class.java)

}