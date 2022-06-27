package com.jetpackcompose.app.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpackcompose.core.util.base.BaseNavGraph
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import javax.inject.Inject

class AppNavGraph @Inject constructor(
    @Screen.Splash.Splash private val splashScreen: BaseScreen,
    @Screen.Home.Home private val homeScreen: BaseScreen
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
                homeScreen.createScreen(navController = navController)
            }
        }
    }
}