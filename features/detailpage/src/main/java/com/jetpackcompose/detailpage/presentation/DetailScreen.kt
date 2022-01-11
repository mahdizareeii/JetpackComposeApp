package com.jetpackcompose.detailpage.presentation

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    argument: Bundle?
) {
    ConstraintLayout {
        val (image, content) = createRefs()

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
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
        ) {
            LazyColumn(Modifier.padding(8.dp)) {
                item {
                    viewModel.detail.value?.title?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colors.textColor,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                }

                item {
                    viewModel.detail.value?.ingredients?.let {
                       /* Text(
                            text = it,
                            color = MaterialTheme.colors.textColor,
                            style = MaterialTheme.typography.body2
                        )*/
                    }
                }
            }
        }
    }
}