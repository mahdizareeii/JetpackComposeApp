package com.jetpackcompose.domain.di

import com.jetpackcompose.domain.network.api.RecipeApiService
import com.jetpackcompose.domain.network.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.network.repository.RecipeRepository
import com.jetpackcompose.domain.network.repository.RecipeRepositoryImpl
import com.jetpackcompose.domain.usecase.GetRecipeByIdUseCase
import com.jetpackcompose.domain.usecase.GetRecipeByIdUseCaseImpl
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.domain.usecase.SearchRecipesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    @Singleton
    @Provides
    fun provideRecipeMapper(): RecipeDtoMapper =
        RecipeDtoMapper()

    @Singleton
    @Provides
    fun provideSearchRecipeUseCase(
        repository: RecipeRepository,
        mapper: RecipeDtoMapper
    ): SearchRecipesUseCase =
        SearchRecipesUseCaseImpl(repository, mapper)

    @Singleton
    @Provides
    fun provideGetRecipeByIdUsCase(
        repository: RecipeRepository,
        mapper: RecipeDtoMapper
    ): GetRecipeByIdUseCase =
        GetRecipeByIdUseCaseImpl(repository, mapper)

    @Singleton
    @Provides
    fun provideRepository(
        apiService: RecipeApiService
    ): RecipeRepository =
        RecipeRepositoryImpl(apiService)

}