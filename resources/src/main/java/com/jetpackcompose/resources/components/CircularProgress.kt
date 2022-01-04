package com.jetpackcompose.resources.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetpackcompose.resources.theme.circularProgressColor

@Composable
fun CircularProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .width(25.dp)
            .height(25.dp),
        color = MaterialTheme.colors.circularProgressColor
    )
}