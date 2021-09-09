package com.jetpackcompose.app.di

import com.jetpackcompose.app.domain.usecase.GetRecipeByIdUseCase
import com.jetpackcompose.app.domain.usecase.GetRecipeByIdUseCaseImpl
import com.jetpackcompose.app.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.app.domain.usecase.SearchRecipesUseCaseImpl
import com.jetpackcompose.app.network.api.RecipeApiService
import com.jetpackcompose.app.network.mapper.RecipeDtoMapper
import com.jetpackcompose.app.network.repository.RecipeRepository
import com.jetpackcompose.app.network.repository.RecipeRepositoryImpl
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
    fun provideRecipeMapper(): RecipeDtoMapper = RecipeDtoMapper()

    @Singleton
    @Provides
    fun provideSearchRecipeUseCase(
        repository: RecipeRepository,
        mapper: RecipeDtoMapper
    ): SearchRecipesUseCase = SearchRecipesUseCaseImpl(repository, mapper)

    @Singleton
    @Provides
    fun provideGetRecipeByIdUsCase(
        repository: RecipeRepository,
        mapper: RecipeDtoMapper
    ): GetRecipeByIdUseCase = GetRecipeByIdUseCaseImpl(repository, mapper)

    @Singleton
    @Provides
    fun provideRepository(
        apiService: RecipeApiService
    ): RecipeRepository = RecipeRepositoryImpl(apiService)

}