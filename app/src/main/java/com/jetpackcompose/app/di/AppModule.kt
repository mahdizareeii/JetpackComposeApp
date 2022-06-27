package com.jetpackcompose.app.di

import com.jetpackcompose.app.presentation.navgraph.AppNavGraph
import com.jetpackcompose.app.presentation.ui.SplashScreen
import com.jetpackcompose.homepage.presentation.HomeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *  you should give the scope to @InstallIn() to inject something based on this document
 *  https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppNavGraph(
        splashScreen: SplashScreen,
        homeScreen: HomeScreen
    ): AppNavGraph = AppNavGraph(
        splashScreen,
        homeScreen
    )

    @Provides
    fun provideSplashScreen(): SplashScreen = SplashScreen()
}