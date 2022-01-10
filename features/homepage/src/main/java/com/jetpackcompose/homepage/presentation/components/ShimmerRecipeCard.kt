package com.jetpackcompose.homepage.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRecipeCard(brush: Brush) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(brush),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(10.dp)
                        .background(brush)
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.1f)
                        .height(10.dp)
                        .background(brush)
                )
            }

        }
    }
}