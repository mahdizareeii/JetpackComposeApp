package com.jetpackcompose.homepage.presentation.navgraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.detailpage.presentation.DetailScreen
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreen
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreen

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun HomeNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomePopular.route
    ) {
        composable(
            route = Screen.HomePopular.route
        ) {
            PopularScreen()
        }

        composable(
            route = Screen.HomeSearch.route
        ) {
            SearchScreen(navController = navController)
        }

        composable(
            route = "${Screen.Detail.route}/{id}",
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