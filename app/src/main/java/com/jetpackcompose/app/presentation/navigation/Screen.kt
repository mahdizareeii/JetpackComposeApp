package com.jetpackcompose.app.presentation.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("detail_screen")

}
