package com.jetpackcompose.core.util.base

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController

interface BaseScreen {
    @Composable
    fun createScreen(
        argument: Bundle?,
        navController: NavHostController
    ) {
    }

    @Composable
    fun createScreen(
        navController: NavHostController
    ) {
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