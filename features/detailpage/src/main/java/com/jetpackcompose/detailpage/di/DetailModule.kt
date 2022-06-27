package com.jetpackcompose.detailpage.di

import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.detailpage.presentation.DetailScreen
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailModule {

    @Screen.Detail.Detail
    @Binds
    abstract fun bindDetailScreen(
        screen: DetailScreen
    ): BaseScreen

}