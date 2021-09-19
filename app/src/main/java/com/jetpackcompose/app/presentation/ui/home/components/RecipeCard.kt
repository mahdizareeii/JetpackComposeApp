package com.jetpackcompose.app.presentation.ui.home.components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.jetpackcompose.app.R
import com.jetpackcompose.domain.model.Recipe

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
        Column {
            recipe.featuredImage?.let { img ->
                //load image with coil
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp),
                    painter = rememberImagePainter(
                        data = img,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.place_holder)
                            scale(Scale.FILL)
                        }
                    ),
                    contentDescription = "food image",
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                recipe.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.8f)
                            .wrapContentWidth(Alignment.Start),
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }

                recipe.rating?.let {
                    Text(
                        text = it.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
            }

        }
    }
}