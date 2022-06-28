package com.jetpackcompose.app.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpackcompose.app.presentation.ui.SplashScreen
import com.jetpackcompose.core.util.base.BaseNavGraph
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.homepage.presentation.HomeScreen
import javax.inject.Inject

class AppNavGraph @Inject constructor(
    private val splashScreen: SplashScreen,
    private val homeScreen: HomeScreen
) : BaseNavGraph {
    @Composable
    override fun createNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
        ) {
            composable(
                route = Screen.Splash.route
            ) {
                splashScreen.createScreen(navController = navController)
            }

            composable(
                route = Screen.Home.route
            ) {
                homeScreen.createScreen(navController = rememberNavController())
            }
        }
    }
}