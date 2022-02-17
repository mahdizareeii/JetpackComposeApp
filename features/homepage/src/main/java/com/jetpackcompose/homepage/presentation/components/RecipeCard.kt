package com.jetpackcompose.homepage.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.homepage.R
import com.jetpackcompose.resources.theme.textColor

@ExperimentalCoilApi
@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                painter = rememberImagePainter(
                    data = recipe.featuredImage,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.place_holder)
                        scale(Scale.FILL)
                    }
                ),
                contentDescription = "list item image",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                Text(
                    text = recipe.title ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.textColor
                )

                Text(
                    text = "rating: ${recipe.rating}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.textColor
                )
            }

        }
    }
}