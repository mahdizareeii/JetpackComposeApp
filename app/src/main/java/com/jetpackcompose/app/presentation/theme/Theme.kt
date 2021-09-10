package com.jetpackcompose.app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryColorDark,
    primaryVariant = PrimaryVariantDark,
    secondary = SecondaryColorDark,
    background = BackgroundColorDark,
    onSurface = White,
    onSecondary = White2
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryVariant,
    secondary = SecondaryColor,
    background = BackgroundColor,
    onSurface = DarkBlue,
    onSecondary = DarkBlue2
)

@Composable
fun ComposeAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}