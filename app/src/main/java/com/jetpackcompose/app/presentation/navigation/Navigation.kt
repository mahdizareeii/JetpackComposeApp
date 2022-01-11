package com.jetpackcompose.app.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.ui.SplashScreen
import com.jetpackcompose.detailpage.presentation.DetailScreen
import com.jetpackcompose.domain.util.navigation.Screen
import com.jetpackcompose.homepage.presentation.MainScreen

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        
        composable(
            route = Screen.HomeScreen.route
        ) {
            MainScreen(navController = navController)
        }

        composable(
            route = "${Screen.DetailScreen.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            DetailScreen(argument = navBackStackEntry.arguments)
        }
    }
}