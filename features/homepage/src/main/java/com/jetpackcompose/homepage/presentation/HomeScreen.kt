package com.jetpackcompose.homepage.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.domain.model.FoodCategory
import com.jetpackcompose.domain.util.compose.getSnackBarCoroutineChannel
import com.jetpackcompose.domain.util.compose.getSnackBarHostState
import com.jetpackcompose.homepage.presentation.components.Chip
import com.jetpackcompose.homepage.presentation.components.RecipeCard
import com.jetpackcompose.homepage.presentation.components.SearchBar
import com.jetpackcompose.resources.components.ErrorListItem
import com.jetpackcompose.resources.components.LoadingListItem
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val channel = getSnackBarCoroutineChannel()
    val snackBarHostState = getSnackBarHostState(channel) { result ->
        when (result) {
            SnackbarResult.ActionPerformed -> {
                //do something when snack bar action button clicked
            }
            SnackbarResult.Dismissed -> {
                //do something when snack bar closed
            }
        }
    }
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackBarHostState)
    val lazyRowState = rememberLazyListState()

    val recipes = viewModel.recipeList.collectAsLazyPagingItems()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SearchBar(viewModel = viewModel)
        },
        content = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                //create reference for the views to constraint
                val (chips, contents) = createRefs()

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(chips) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    state = lazyRowState
                ) {
                    items(FoodCategory.getAllFood()) {
                        Chip(
                            text = it.foodName,
                            viewModel = viewModel,
                            lazyListState = lazyRowState
                        )
                    }

                    //for scroll to previous position automatically (when change screen orientation)
                    coroutineScope.launch {
                        lazyRowState.animateScrollToItem(
                            index = viewModel.lazyRowScrollIndexPosition,
                            scrollOffset = viewModel.lazyRowScrollOffsetPosition
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(contents) {
                            top.linkTo(chips.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)

                            //like 0dp in xml
                            height = Dimension.fillToConstraints
                        }
                ) {
                    items(recipes) { recipe ->
                        RecipeCard(recipe = recipe!!, onClick = {})
                    }

                    recipes.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                channel.trySend(
                                    "Fetching data"
                                )
                            }

                            loadState.refresh is LoadState.Error -> {
                                channel.trySend(
                                    (loadState.refresh as LoadState.Error).error.message ?: ""
                                )
                            }

                            loadState.append is LoadState.Loading -> {
                                item {
                                    LoadingListItem()
                                }
                            }

                            loadState.append is LoadState.Error -> {
                                item {
                                    ErrorListItem(
                                        error = (loadState.append as LoadState.Error).error.message,
                                        onTryClicked = {
                                            retry()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}