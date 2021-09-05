package com.jetpackcompose.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jetpackcompose.app.presentation.ui.DetailScreen
import com.jetpackcompose.app.presentation.ui.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val route = Screen.DetailScreen.route
    NavHost(navController = navController, startDestination = route) {
        composable(route = route) {
            MainScreen(navController = navController)
        }

        composable(
            route = "$route/{title}",
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