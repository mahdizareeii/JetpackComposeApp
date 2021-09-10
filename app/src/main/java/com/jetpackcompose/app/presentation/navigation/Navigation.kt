package com.jetpackcompose.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jetpackcompose.app.presentation.ui.detail.DetailScreen
import com.jetpackcompose.app.presentation.ui.home.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = "${Screen.DetailScreen.route}/{title}",
            arguments = listOf(
                navArgument("title") {
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