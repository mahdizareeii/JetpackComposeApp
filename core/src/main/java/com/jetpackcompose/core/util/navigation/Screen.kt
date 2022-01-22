package com.jetpackcompose.core.util.navigation

import com.jetpackcompose.core.R

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: Int = 0
) {

    object Splash : Screen("splash_screen")

    object Home : Screen("home_screen")

    object Detail : Screen("detail_screen")

    object HomeSearch : Screen(
        "home_search_screen",
        "Search",
        R.drawable.ic_search_24
    )

    object HomePopular : Screen(
        "home_popular_screen",
        "Popular",
        R.drawable.ic_star_24
    )

}
