package com.jetpackcompose.homepage.di

import com.jetpackcompose.detailpage.presentation.DetailScreen
import com.jetpackcompose.homepage.presentation.HomeScreen
import com.jetpackcompose.homepage.presentation.navgraph.HomeNavGraph
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreen
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreenViewModel
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomePageModule {
    @Provides
    fun bindHomePageNavGraph(
        popularScreen: PopularScreen,
        searchScreen: SearchScreen,
        detailScreen: DetailScreen
    ): HomeNavGraph = HomeNavGraph(popularScreen, searchScreen, detailScreen)

    @Provides
    fun bindHomeScreen(
        homeNavGraph: HomeNavGraph
    ): HomeScreen = HomeScreen(homeNavGraph)

    @Provides
    fun bindHomePopular(): PopularScreen = PopularScreen()
}