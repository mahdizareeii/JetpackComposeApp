package com.jetpackcompose.homepage.presentation.screens.popular

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.domain.model.PopularScreenUIState
import com.jetpackcompose.homepage.presentation.components.RecipeCheapProduct
import com.jetpackcompose.homepage.presentation.components.RecipeDessert
import com.jetpackcompose.homepage.presentation.components.RecipeMostSell
import com.jetpackcompose.resources.components.CircularProgress
import com.jetpackcompose.resources.components.ScreenError
import javax.inject.Inject

class PopularScreen @Inject constructor() : BaseScreen {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalCoilApi::class)
    @Composable
    override fun onScreenCreated(argument: Bundle?, navController: NavHostController) {
        val viewModel: PopularScreenViewModel = hiltViewModel()
        var loadingVisibility by remember {
            mutableStateOf(false)
        }

        var errorVisibility by remember {
            mutableStateOf(false)
        }

        var contentVisibility by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(viewModel.popularList.value) {
            contentVisibility = viewModel.popularList.value.isNotEmpty()
        }

        LaunchedEffect(viewModel.loading.value) {
            loadingVisibility = viewModel.loading.value
        }

        LaunchedEffect(viewModel.error.value) {
            errorVisibility = viewModel.error.value != null
        }

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (loading, error, content) = createRefs()

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
                    viewModel.getPopularList()
                }
            }


            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                visible = contentVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { contentDescription = "popular items" },
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(viewModel.popularList.value) {
                        when (it) {
                            is PopularScreenUIState.MostSells -> {
                                LazyRow(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(it.list) { recipe ->
                                        RecipeMostSell(
                                            recipe = recipe,
                                            onClick = {
                                                navController.navigate(
                                                    "${Screen.Detail.route}/${recipe.id}"
                                                )
                                            }
                                        )
                                    }
                                }
                            }

                            is PopularScreenUIState.CheapProducts -> {
                                LazyRow(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(it.list) { recipe ->
                                        RecipeCheapProduct(
                                            recipe = recipe,
                                            onClick = {
                                                navController.navigate(
                                                    "${Screen.Detail.route}/${recipe.id}"
                                                )
                                            }
                                        )
                                    }
                                }
                            }

                            is PopularScreenUIState.DessertProducts -> {
                                LazyRow(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(it.list) { recipe ->
                                        RecipeDessert(
                                            recipe = recipe,
                                            onClick = {
                                                navController.navigate(
                                                    "${Screen.Detail.route}/${recipe.id}"
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}