package com.jetpackcompose.repository.di

import com.jetpackcompose.network.api.RecipeApiService
import com.jetpackcompose.repository.repository.RecipeRepository
import com.jetpackcompose.repository.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        apiService: RecipeApiService
    ): RecipeRepository = RecipeRepositoryImpl(
        apiService = apiService,
        dispatcher = Dispatchers.Default
    )
}