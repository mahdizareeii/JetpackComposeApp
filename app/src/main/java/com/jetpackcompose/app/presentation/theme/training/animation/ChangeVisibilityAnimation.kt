package com.jetpackcompose.app.presentation.theme.training.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ChangeVisibilityOfViewWithAnimation() {
    var visible by remember {
        mutableStateOf(true)
    }

    Button(
        onClick = {
            visible = !visible
        }
    ) {
        Text(text = if (visible) "Hide" else "Show")
    }

    Spacer(modifier = Modifier.height(20.dp))

    AnimatedVisibility(visible = visible) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Color.Red)
        )
    }

}