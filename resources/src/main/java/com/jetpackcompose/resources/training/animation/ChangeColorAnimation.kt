package com.jetpackcompose.resources.training.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChangeColorOfButtonWithAnimation() {
    var blue by remember {
        mutableStateOf(true)
    }

    val color by animateColorAsState(if (blue) Color.Blue else Color.Red)

    Button(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        onClick = { blue = !blue },
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(
            text = "Click me"
        )
    }
}