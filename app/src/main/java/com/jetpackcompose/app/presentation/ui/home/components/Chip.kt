package com.jetpackcompose.app.presentation.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcompose.app.presentation.theme.AppTheme

@Preview(name = "Light Mode")
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewChip() {
    AppTheme {
        Chip(text = "Test") {}
    }
}

@Composable
fun Chip(text: String, clicked: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .clickable { clicked.invoke(text) },
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.onBackground
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.caption
        )
    }
}