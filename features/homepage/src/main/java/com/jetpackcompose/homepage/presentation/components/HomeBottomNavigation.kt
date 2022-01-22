package com.jetpackcompose.homepage.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.resources.theme.bottomNavigationColor
import com.jetpackcompose.resources.theme.chipSelected
import com.jetpackcompose.resources.theme.textColor

@Composable
fun HomeBottomNavigation(navController: NavController) {

    val bottomNavigationItems = listOf(
        Screen.HomePopular,
        Screen.HomeSearch
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomNavigationColor,
        contentColor = MaterialTheme.colors.chipSelected
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                label = {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.chipSelected
                    )
                },
                icon = {
                    painterResource(id = it.icon)
                },
                selectedContentColor = MaterialTheme.colors.textColor,
                unselectedContentColor = MaterialTheme.colors.textColor.copy(0.4f),
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(
                        it.route
                    ) {
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }

}