package com.jetpackcompose.homepage.di

import com.jetpackcompose.core.util.base.BaseNavGraph
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.core.util.qualifiers.Qualifiers
import com.jetpackcompose.homepage.presentation.HomeScreen
import com.jetpackcompose.homepage.presentation.navgraph.HomeNavGraph
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreen
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomePageModule {
    @Qualifiers.NavGraphs.HomeNavGraph
    @Binds
    abstract fun bindHomePageNavGraph(
        navGraph: HomeNavGraph
    ): BaseNavGraph

    @Screen.Home.Home
    @Binds
    abstract fun bindHomeScreen(
        screen: HomeScreen
    ): BaseScreen

    @Screen.HomePopular.Popular
    @Binds
    abstract fun bindHomePopular(
        screen: PopularScreen
    ): BaseScreen
}