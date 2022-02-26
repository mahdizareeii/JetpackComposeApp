package com.jetpackcompose.domain.di

import com.jetpackcompose.domain.mapper.RecipeDtoMapper
import com.jetpackcompose.domain.usecase.GetPopularRecipesUseCase
import com.jetpackcompose.domain.usecase.GetRecipeByIdUseCase
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.repository.repository.RecipeRepository
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
    ): SearchRecipesUseCase = SearchRecipesUseCase(repository, mapper)

    @Singleton
    @Provides
    fun provideGetRecipeByIdUsCase(
        repository: RecipeRepository,
        mapper: RecipeDtoMapper
    ): GetRecipeByIdUseCase = GetRecipeByIdUseCase(repository, mapper)

    @Singleton
    @Provides
    fun provideGetPopularRecipesUseCase(
        repository: RecipeRepository,
        mapper: RecipeDtoMapper
    ): GetPopularRecipesUseCase = GetPopularRecipesUseCase(
        repository, mapper
    )

}