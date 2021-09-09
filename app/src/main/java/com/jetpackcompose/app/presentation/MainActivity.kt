package com.jetpackcompose.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jetpackcompose.app.presentation.navigation.Navigation
import com.jetpackcompose.app.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                Navigation()
            }
        }
    }
}