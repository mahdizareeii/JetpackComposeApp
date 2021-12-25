package com.jetpackcompose.app.presentation.theme.training.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChangeContentSizeOfViewWithAnimation() {
    var expanded by remember {
        mutableStateOf(false)
    }

    Button(
        onClick = {
            expanded = !expanded
        }
    ) {
        Text(text = if (expanded) "SHRINK" else "EXPAND")
    }

    Spacer(modifier = Modifier.height(20.dp))

    Box(
        modifier = Modifier
            .background(Color.Red)
            .animateContentSize()
    ) {
        Text(
            text = sampleText,
            color = Color.White,
            maxLines = if (expanded) Int.MAX_VALUE else 2
        )
    }
}

private const val sampleText =
    "Animations are essential in a modern mobile app in order to realize a smooth and understandable user experience. Many Jetpack Compose Animation APIs are available as composable functions just like layouts and other UI elements, and they are backed by lower-level APIs built with Kotlin coroutine suspend functions. This guide starts with the high-level APIs that are useful in many practical scenarios, and moves on to explain the low-level APIs that give you further control and customization."