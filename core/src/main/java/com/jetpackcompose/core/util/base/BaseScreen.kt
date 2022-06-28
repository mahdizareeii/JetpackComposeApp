package com.jetpackcompose.core.util.base

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController

interface BaseScreen {

    @Composable
    fun onScreenCreated(
        argument: Bundle?,
        navController: NavHostController
    )

    @Composable
    fun createScreen(
        argument: Bundle?,
        navController: NavHostController
    ) {
        onScreenCreated(argument = argument, navController = navController)
    }

    @Composable
    fun createScreen(
        navController: NavHostController
    ) {
        onScreenCreated(argument = null, navController = navController)
    }

    fun getArgument(
        navController: NavController
    ) = navController.previousBackStackEntry?.savedStateHandle

    fun putArgument(
        navController: NavController,
        savedStateHandle: SavedStateHandle.() -> Unit
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.let {
            savedStateHandle.invoke(it)
        }
    }
}