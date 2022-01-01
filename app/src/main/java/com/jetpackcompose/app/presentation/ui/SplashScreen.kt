package com.jetpackcompose.app.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jetpackcompose.app.presentation.navigation.Screen
import com.jetpackcompose.app.presentation.ui.training.animation.ChangeBackgroundColorOfViewWithInfiniteAnimation
import com.jetpackcompose.resources.theme.splashColor1
import com.jetpackcompose.resources.theme.splashColor2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = {
            coroutineScope.launch {
                delay(3000)
                navController.navigate(Screen.HomeScreen.route)
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChangeBackgroundColorOfViewWithInfiniteAnimation(
                    MaterialTheme.colors.splashColor1,
                    MaterialTheme.colors.splashColor2
                )
            }
        }
    )
}