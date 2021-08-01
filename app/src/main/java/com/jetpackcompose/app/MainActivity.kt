package com.jetpackcompose.app

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcompose.app.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                Column() {
                    MessageCard(msg = Message("Mahdi", "Hi"))
                    MessageCard(msg = Message("Mahdi", "Hi"))
                    MessageCard(msg = Message("Mahdi", "Hi"))
                    MessageCard(msg = Message("Mahdi", "Hi"))
                    MessageCard(msg = Message("Mahdi", "Hi"))
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 5.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "user image profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.onPrimary, CircleShape)
        )

        Spacer(Modifier.width(8.dp))

        Column() {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = msg.body,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposeAppTheme {
        MessageCard(msg = Message("Mahdi", "hi im android developer"))
    }
}