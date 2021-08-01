package com.jetpackcompose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jetpackcompose.app.ui.elements.Conversation
import com.jetpackcompose.app.ui.elements.getSampleMessages
import com.jetpackcompose.app.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                Conversation(messages = getSampleMessages())
            }
        }
    }
}