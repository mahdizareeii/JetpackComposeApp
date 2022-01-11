package com.jetpackcompose.detailpage.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetpackcompose.resources.theme.textColor

@ExperimentalFoundationApi
@Composable
fun DetailContent(title: String, list: List<String>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.textColor,
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.padding(5.dp))

        LazyColumn {
            items(list) { item ->
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = item,
                    color = MaterialTheme.colors.textColor,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}