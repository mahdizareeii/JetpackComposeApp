package com.jetpackcompose.detailpage.presentation

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetpackcompose.detailpage.presentation.components.DetailContent
import com.jetpackcompose.resources.components.CircularProgress
import com.jetpackcompose.resources.components.ScreenError

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    argument: Bundle?
) {
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