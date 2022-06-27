package com.jetpackcompose.detailpage.di

import com.jetpackcompose.detailpage.presentation.DetailScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {

    @Provides
    fun bindDetailScreen(): DetailScreen = DetailScreen()

}