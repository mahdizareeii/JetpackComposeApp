package com.jetpackcompose.homepage.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jetpackcompose.core.util.base.BaseNavGraph
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.detailpage.presentation.DetailScreen
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreen
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreen
import javax.inject.Inject

class HomeNavGraph @Inject constructor(
    private val popularScreen: PopularScreen,
    private val searchScreen: SearchScreen,
    private val detailScreen: DetailScreen
) : BaseNavGraph {
    @Composable
    override fun createNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomePopular.route
        ) {
            composable(
                route = Screen.HomePopular.route
            ) {
                popularScreen.createScreen(navController = navController)
            }

            composable(
                route = Screen.HomeSearch.route
            ) {
                searchScreen.createScreen(navController = navController)
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
                detailScreen.createScreen(
                    argument = navBackStackEntry.arguments,
                    navController = navController
                )
            }
        }
    }
}