package com.jetpackcompose.resources.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColor = darkColors(
    primary = dark,
    secondary = black,
    background = black
)

private val LightColor = lightColors(
    primary = white,
    secondary = dark,
    background = white
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) DarkColor else LightColor,
        typography = CustomTypography,
        shapes = Shapes,
        content = content
    )
}