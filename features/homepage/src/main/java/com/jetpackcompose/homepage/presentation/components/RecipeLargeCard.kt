package com.jetpackcompose.homepage.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.resources.components.SquareView

@ExperimentalCoilApi
@Composable
fun RecipeLargeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    recipe.featuredImage?.let {
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            painter = rememberImagePainter(
                data = it,
                builder = {
                    crossfade(true)
                    scale(Scale.FILL)
                }
            ),
            contentDescription = "detail image",
            contentScale = ContentScale.Crop
        )
    }
}