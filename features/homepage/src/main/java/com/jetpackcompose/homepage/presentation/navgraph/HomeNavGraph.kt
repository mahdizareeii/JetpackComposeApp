package com.jetpackcompose.homepage.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jetpackcompose.core.util.base.BaseNavGraph
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import javax.inject.Inject

class HomeNavGraph @Inject constructor(
    @Screen.HomePopular.Popular
    private val popularScreen: BaseScreen,
    @Screen.HomeSearch.Search
    private val searchScreen: BaseScreen,
    @Screen.Detail.Detail
    private val detailScreen: BaseScreen
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