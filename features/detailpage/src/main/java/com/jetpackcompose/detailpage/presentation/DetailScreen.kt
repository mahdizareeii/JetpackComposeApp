package com.jetpackcompose.detailpage.presentation

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jetpackcompose.resources.theme.textColor

@Composable
fun DetailScreen(argument: Bundle?) {
    val title: String = argument?.getString("id") ?: ""
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.textColor
        )
    }
}