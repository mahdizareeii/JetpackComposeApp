package com.jetpackcompose.resources.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jetpackcompose.resources.theme.textColor

@ExperimentalAnimationApi
@Composable
fun ScreenError(
    modifier: Modifier,
    errorMessage: String?,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            text = errorMessage ?: "Unknown error",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.textColor
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            onClick = onRetryClicked
        ) {
            Text(
                text = "retry",
                color = MaterialTheme.colors.textColor,
                style = MaterialTheme.typography.caption
            )
        }
    }
}