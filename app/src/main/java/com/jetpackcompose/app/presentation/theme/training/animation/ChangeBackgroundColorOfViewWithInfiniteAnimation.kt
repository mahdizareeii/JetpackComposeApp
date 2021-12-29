package com.jetpackcompose.app.presentation.theme.training.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ChangeBackgroundColorOfViewWithInfiniteAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val color by infiniteTransition.animateColor(
        initialValue = Color.Green,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
    )
}