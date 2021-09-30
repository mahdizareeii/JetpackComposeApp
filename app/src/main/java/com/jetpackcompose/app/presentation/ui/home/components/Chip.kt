package com.jetpackcompose.app.presentation.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(text: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = text,
        color = MaterialTheme.colors.secondary
    )
}