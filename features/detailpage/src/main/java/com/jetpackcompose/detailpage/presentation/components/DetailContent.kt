package com.jetpackcompose.detailpage.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.jetpackcompose.detailpage.presentation.DetailScreenViewModel
import com.jetpackcompose.resources.components.SquareView
import com.jetpackcompose.resources.theme.textColor

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun DetailContent(
    viewModel: DetailScreenViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .semantics { contentDescription = "detail" }
            .fillMaxSize()
    ) {
        val (image, content) = createRefs()

        val guideline = createGuidelineFromTop(0.3f)

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
                .constrainAs(content) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = viewModel.detail.value?.title ?: "",
                    color = MaterialTheme.colors.textColor,
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(modifier = Modifier.padding(5.dp))

                LazyColumn {
                    items(viewModel.detail.value?.ingredients ?: listOf()) { item ->
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
    }
}