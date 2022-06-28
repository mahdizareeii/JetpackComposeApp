package com.jetpackcompose.homepage.presentation

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.homepage.presentation.components.HomeBottomNavigation
import com.jetpackcompose.homepage.presentation.navgraph.HomeNavGraph
import javax.inject.Inject

class HomeScreen @Inject constructor(
    private val homeNavGraph: HomeNavGraph
) : BaseScreen {
    @Composable
    override fun onScreenCreated(argument: Bundle?, navController: NavHostController) {
        Scaffold(
            bottomBar = {
                HomeBottomNavigation(navController)
            },
            content = { contentPadding ->
                Box(modifier = Modifier.padding(contentPadding)) {
                    homeNavGraph.createNavGraph(navController)
                }
            }
        )
    }
}