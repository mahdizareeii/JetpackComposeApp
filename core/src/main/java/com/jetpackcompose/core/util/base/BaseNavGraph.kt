package com.jetpackcompose.core.util.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface BaseNavGraph {
    @Composable
    fun createNavGraph(navController: NavHostController)
}