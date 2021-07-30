package com.jetpackcompose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcompose.app.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                MessageCard(
                    Message(
                        author = "Mahdi Zareei",
                        body = "is android developer"
                    )
                )
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Column() {
        Text(text = msg.author)
        Text(text = msg.body)
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(msg = Message("Hi", "Dear"))
}