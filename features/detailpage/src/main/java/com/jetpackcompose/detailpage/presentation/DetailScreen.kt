package com.jetpackcompose.detailpage.presentation

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.resources.components.CircularProgress
import com.jetpackcompose.resources.components.ScreenError
import com.jetpackcompose.resources.components.SquareView
import com.jetpackcompose.resources.theme.textColor
import javax.inject.Inject
class DetailScreen @Inject constructor() : BaseScreen {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun onScreenCreated(argument: Bundle?, navController: NavHostController) {
        val viewModel: DetailScreenViewModel = hiltViewModel()
        var loadingVisibility by remember {
            mutableStateOf(false)
        }

        var errorVisibility by remember {
            mutableStateOf(false)
        }

        var contentVisibility by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(viewModel.detail.value) {
            contentVisibility = viewModel.detail.value != null
        }

        LaunchedEffect(viewModel.error.value) {
            errorVisibility = viewModel.error.value != null
        }

        LaunchedEffect(viewModel.loading.value) {
            loadingVisibility = viewModel.loading.value
        }

        ConstraintLayout(
            modifier = Modifier
                .semantics { contentDescription = "detail" }
                .fillMaxSize()
        ) {
            val (allViews, error, loading) = createRefs()

            AnimatedVisibility(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(loading) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                visible = loadingVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CircularProgress(
                    Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
            }

            AnimatedVisibility(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(error) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                visible = errorVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ScreenError(
                    modifier = Modifier.wrapContentSize(),
                    errorMessage = viewModel.error.value
                ) {
                    viewModel.getRecipeById()
                }
            }

            AnimatedVisibility(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(allViews) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                visible = contentVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                DetailContent(
                    viewModel = viewModel
                )
            }
        }
    }

    @OptIn(ExperimentalCoilApi::class)
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
                    }
                    .fillMaxWidth(),
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
}