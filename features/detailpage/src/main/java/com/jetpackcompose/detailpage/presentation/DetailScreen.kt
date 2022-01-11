package com.jetpackcompose.detailpage.presentation

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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

        viewModel.detail.value?.featuredImage?.let {
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
                        data = it,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.place_holder)
                            scale(Scale.FILL)
                        }
                    ),
                    contentDescription = "detail image"
                )
            }
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