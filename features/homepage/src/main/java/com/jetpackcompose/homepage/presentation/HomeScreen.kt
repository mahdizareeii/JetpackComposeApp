package com.jetpackcompose.homepage.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.homepage.presentation.components.HomeBottomNavigation
import com.jetpackcompose.homepage.presentation.navgraph.HomeNavGraph

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            HomeBottomNavigation(navController)
        },
        content = { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                HomeNavGraph(navController)
            }
        }
    )
}