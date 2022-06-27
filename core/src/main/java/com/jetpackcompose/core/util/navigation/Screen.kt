package com.jetpackcompose.core.util.navigation

import com.jetpackcompose.core.R
import javax.inject.Qualifier

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: Int = 0,
) {

    object Splash : Screen(route = "splash_screen")

    object Home : Screen(route = "home_screen")

    object Detail : Screen(route = "detail_screen")

    object HomeSearch : Screen(
        route = "home_search_screen",
        title = "Search",
        icon = R.drawable.ic_search_24
    )

    object HomePopular : Screen(
        route = "home_popular_screen",
        title = "Popular",
        icon = R.drawable.ic_star_24
    )

}
