package com.jetpackcompose.app.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.navigation.Navigation
import com.jetpackcompose.app.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Navigation()
            }
        }
    }
}