package com.jetpackcompose.resources.training

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcompose.resources.R
import com.jetpackcompose.resources.theme.AppTheme

/**
 * https://developer.android.com/jetpack/compose/tutorial
 */

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    AppTheme {
        Conversation(messages = getSampleMessages())
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

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(5.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor: Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.secondary else MaterialTheme.colors.background
            )

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
                    .clickable { isExpanded = !isExpanded }
            ) {
                Text(
                    text = msg.body,
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.padding(all = 5.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

/***
 * We need to create a Conversation function that will show multiple messages.
 * For this use case, we can use Compose’s LazyColumn and LazyRow.
 * These composables render only the elements that are visible on screen,
 * so they are designed to be very efficient for long lists. At the same time,
 * they avoid the complexity of RecyclerView with XML layouts.
 * In this code snippet, you can see that LazyColumn has an items child.
 * It takes a List as a parameter and its lambda receives a parameter
 * we’ve named message (we could have named it whatever we want) which is an instance of Message.
 * In short, this lambda is called for each item of the provided List:
 * Import this sample dataset into your project to help bootstrap the conversation quickly:
 */
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}

fun getSampleMessages() = listOf(
    Message("Mahdi", "Hi"),
    Message("Lucy", "Hi"),
    Message("Mahdi", "How are you?"),
    Message("Lucy", "Im fine thanks and you?"),
    Message("Mahdi", "Im fine too"),
    Message(
        "Mahdi",
        "We want you to understand the types of information we collect as you use our services\n" +
                "\n" +
                "We collect information to provide better services to all our users — from figuring out basic stuff like which language you speak, to more complex things like which ads you’ll find most useful, the people who matter most to you online, or which YouTube videos you might like. The information Google collects, and how that information is used, depends on how you use our services and how you manage your privacy controls.\n" +
                "\n" +
                "When you’re not signed in to a Google Account, we store the information we collect with unique identifiers tied to the browser, application, or device you’re using. This helps us do things like maintain your language preferences across browsing sessions.\n" +
                "\n" +
                "When you’re signed in, we also collect information that we store with your Google Account, which we treat as personal information."
    ),
    Message(
        "Lucy",
        "We collect information about the apps, browsers, and devices you use to access Google services, which helps us provide features like automatic product updates and dimming your screen if your battery runs low.\n" +
                "\n" +
                "The information we collect includes unique identifiers, browser type and settings, device type and settings, operating system, mobile network information including carrier name and phone number, and application version number. We also collect information about the interaction of your apps, browsers, and devices with our services, including IP address, crash reports, system activity, and the date, time, and referrer URL of your request.\n" +
                "\n" +
                "We collect this information when a Google service on your device contacts our servers — for example, when you install an app from the Play Store or when a service checks for automatic updates. If you’re using an Android device with Google apps, your device periodically contacts Google servers to provide information about your device and connection to our services. This information includes things like your device type, carrier name, crash reports, and which apps you've installed."
    )
)