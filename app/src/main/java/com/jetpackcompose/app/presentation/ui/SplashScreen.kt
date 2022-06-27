package com.jetpackcompose.app.presentation.ui

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.resources.theme.textColor
import kotlinx.coroutines.delay
import javax.inject.Inject

class SplashScreen @Inject constructor() : BaseScreen {
    @Composable
    override fun createScreen(argument: Bundle?, navController: NavHostController) {
        val textVisibility = remember {
            MutableTransitionState(false).apply {
                //start animation immediately
                targetState = true
            }
        }

        LaunchedEffect(true) {
            delay(3000)
            navController.navigate(Screen.Home.route) {
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
                    AnimatedVisibility(
                        visibleState = textVisibility,
                        enter = fadeIn(animationSpec = tween(3000)) +
                                slideInVertically(
                                    animationSpec = tween(3000),
                                    initialOffsetY = { (it / 0.5).toInt() }
                                )
                    ) {
                        Text(
                            text = "Jetpack Compose",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.textColor
                        )
                    }
                }
            }
        )
    }
}