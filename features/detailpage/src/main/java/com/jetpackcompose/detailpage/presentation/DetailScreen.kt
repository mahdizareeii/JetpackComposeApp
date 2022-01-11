package com.jetpackcompose.detailpage.presentation

import android.os.Bundle
import androidx.compose.animation.animateContentSize
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
import com.jetpackcompose.detailpage.R
import com.jetpackcompose.resources.components.SquareView
import com.jetpackcompose.resources.theme.textColor

@OptIn(ExperimentalFoundationApi::class)
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
                        placeholder(R.drawable.place_holder)
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
                .height(if (expanded) Int.MAX_VALUE.dp else 10.dp)
                .animateContentSize()
                .constrainAs(content) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
        ) {
            Column(Modifier.padding(8.dp)) {
                viewModel.detail.value?.title?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colors.textColor,
                        style = MaterialTheme.typography.body1
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                viewModel.detail.value?.ingredients?.let { list ->
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
        }
    }
}