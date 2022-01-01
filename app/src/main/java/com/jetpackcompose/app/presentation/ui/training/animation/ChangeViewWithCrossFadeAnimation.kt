package com.jetpackcompose.app.presentation.ui.training.animation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChangeViewWithCrossFadeAnimation() {
    var scene by remember {
        mutableStateOf(ViewScene.Text)
    }

    Button(onClick = {
        scene = when (scene) {
            ViewScene.Icon -> ViewScene.Text
            ViewScene.Text -> ViewScene.Icon
        }
    }) {
        Text(text = "TOGGLE")
    }
    
    Spacer(modifier = Modifier.height(20.dp))

    Column(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Crossfade(targetState = scene) { currentScene ->
            when (currentScene) {
                ViewScene.Text -> Text(text = "Phone")
                ViewScene.Icon -> Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "phoneIcon"
                )
            }
        }
    }
}

enum class ViewScene {
    Text,
    Icon
}