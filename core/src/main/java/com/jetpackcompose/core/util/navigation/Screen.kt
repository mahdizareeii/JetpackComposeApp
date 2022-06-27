package com.jetpackcompose.core.util.navigation

import com.jetpackcompose.core.R
import javax.inject.Qualifier

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: Int = 0,
) {

    object Splash : Screen(route = "splash_screen") {
        @Qualifier
        @Retention(AnnotationRetention.BINARY)
        annotation class Splash
    }

    object Home : Screen(route = "home_screen") {
        @Qualifier
        @Retention(AnnotationRetention.BINARY)
        annotation class Home
    }

    object Detail : Screen(route = "detail_screen") {
        @Qualifier
        @Retention(AnnotationRetention.BINARY)
        annotation class Detail
    }

    object HomeSearch : Screen(
        route = "home_search_screen",
        title = "Search",
        icon = R.drawable.ic_search_24
    ) {
        @Qualifier
        @Retention(AnnotationRetention.BINARY)
        annotation class Search
    }

    object HomePopular : Screen(
        route = "home_popular_screen",
        title = "Popular",
        icon = R.drawable.ic_star_24
    ) {
        @Qualifier
        @Retention(AnnotationRetention.BINARY)
        annotation class Popular
    }

}
