package com.jetpackcompose.app.presentation.navgraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.ui.SplashScreen
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.homepage.presentation.HomeScreen

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {

        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(navController = navController)
        }
        
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen()
        }
    }
}