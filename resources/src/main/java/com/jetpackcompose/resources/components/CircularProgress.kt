package com.jetpackcompose.resources.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jetpackcompose.resources.theme.circularProgressColor

@Composable
fun CircularProgress(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = MaterialTheme.colors.circularProgressColor
    )
}