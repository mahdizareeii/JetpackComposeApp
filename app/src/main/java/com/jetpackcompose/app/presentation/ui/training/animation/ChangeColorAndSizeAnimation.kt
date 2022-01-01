package com.jetpackcompose.app.presentation.ui.training.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
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
fun ChangeColorAndSizeOfButtonWithAnimation() {
    var buttonState by remember {
        mutableStateOf(ButtonState.Small)
    }

    val transition = updateTransition(targetState = buttonState, label = "button transition")

    val color by transition.animateColor(label = "button animate color state") { state ->
        when (state) {
            ButtonState.Small -> Color.Blue
            ButtonState.Large -> Color.Green
        }
    }

    val size by transition.animateDp(label = "button animate size state") { state ->
        when (state) {
            ButtonState.Small -> 100.dp
            ButtonState.Large -> 200.dp
        }
    }

    Button(
        modifier = Modifier
            .width(size)
            .height(size),
        onClick = {
            buttonState = when (buttonState) {
                ButtonState.Small -> ButtonState.Large
                ButtonState.Large -> ButtonState.Small
            }
        },
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(
            text = "Click me"
        )
    }

}

enum class ButtonState {
    Small,
    Large
}