package com.jetpackcompose.detailpage.presentation

import android.os.Bundle
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.jetpackcompose.resources.components.CircularProgress
import com.jetpackcompose.resources.components.SquareView
import com.jetpackcompose.resources.theme.textColor

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    argument: Bundle?
) {
    ConstraintLayout {
        val (image, content) = createRefs()

        val guideline = createGuidelineFromTop(0.3f)

        var expanded by remember {
            mutableStateOf(false)
        }

        viewModel.loading.value.let {
            expanded = !it
        }

        SquareView(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .alpha(0.5f)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = rememberImagePainter(
                    data = viewModel.detail.value?.featuredImage,
                    builder = {
                        crossfade(true)
                        scale(Scale.FILL)
                    }
                ),
                contentDescription = "detail image",
                contentScale = ContentScale.Crop
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .constrainAs(content) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
        ) {
            AnimatedContent(
                targetState = expanded
            ) { target ->
                if (target) {
                    DetailContent(
                        title = viewModel.detail.value?.title.toString(),
                        list = viewModel.detail.value?.ingredients ?: listOf()
                    )
                } else {
                    CircularProgress(
                        Modifier.alpha(0.1f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailContent(title: String, list: List<String>) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = title,
            color = MaterialTheme.colors.textColor,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.padding(10.dp))

        LazyVerticalGrid(cells = GridCells.Fixed(1)) {
            items(list.size) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = list[it],
                    color = MaterialTheme.colors.textColor,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}