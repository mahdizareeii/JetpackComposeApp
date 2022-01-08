package com.jetpackcompose.app.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jetpackcompose.domain.util.navigation.Screen
import com.jetpackcompose.resources.theme.splashColor1
import com.jetpackcompose.resources.theme.splashColor2
import com.jetpackcompose.resources.training.animation.ChangeBackgroundColorOfViewWithInfiniteAnimation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    LaunchedEffect(true) {
        delay(3000)
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    Scaffold(
        content = {
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