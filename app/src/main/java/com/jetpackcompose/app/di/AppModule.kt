package com.jetpackcompose.app.di

import com.jetpackcompose.app.presentation.navgraph.AppNavGraph
import com.jetpackcompose.app.presentation.ui.SplashScreen
import com.jetpackcompose.core.util.base.BaseNavGraph
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.core.util.qualifiers.Qualifiers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *  you should give the scope to @InstallIn() to inject something based on this document
 *  https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Qualifiers.NavGraphs.AppNavGraph
    @Binds
    abstract fun bindAppNavGraph(
        navGraph: AppNavGraph
    ): BaseNavGraph


    @Screen.Splash.Splash
    @Binds
    abstract fun provideSplashScreen(
        screen: SplashScreen
    ): BaseScreen
}