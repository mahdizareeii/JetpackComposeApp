package com.jetpackcompose.resources.components

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun ShimmerAnimationBrush(brush: @Composable (Brush) -> Unit) {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    brush.invoke(
        Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(0.9f),
                Color.LightGray.copy(0.2f),
                Color.LightGray.copy(0.9f)
            ),
            start = Offset(10f, 10f),
            end = Offset(translateAnim, translateAnim)
        )
    )
}