package com.jetpackcompose.network.di

import com.jetpackcompose.network.api.RecipeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RecipeApiService =
        retrofit.create(RecipeApiService::class.java)

}