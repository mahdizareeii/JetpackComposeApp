package com.jetpackcompose.homepage.presentation.screens.search

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.util.base.BaseScreen
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.domain.model.FoodCategory
import com.jetpackcompose.homepage.presentation.components.Chip
import com.jetpackcompose.homepage.presentation.components.RecipeCard
import com.jetpackcompose.homepage.presentation.components.SearchBar
import com.jetpackcompose.homepage.presentation.components.ShimmerRecipeCard
import com.jetpackcompose.resources.components.ErrorListItem
import com.jetpackcompose.resources.components.LoadingListItem
import com.jetpackcompose.resources.components.ShimmerAnimationBrush
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
class SearchScreen @Inject constructor(
    private val viewModel: SearchScreenViewModel
) : BaseScreen {
    @Composable
    override fun createScreen(argument: Bundle?, navController: NavHostController) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        val lazyRowState = rememberLazyListState()
        val lazyColumnState = rememberLazyListState()

        var shimmerVisibility by remember { mutableStateOf(false) }
        val recipes = viewModel.recipeList.collectAsLazyPagingItems()

        val refreshPage: () -> Unit = {
            coroutineScope.launch {
                recipes.refresh()
                //for scroll to top when page refreshed
                lazyColumnState.animateScrollToItem(index = 0, scrollOffset = 0)
            }
        }

        val changeShimmerVisibility: (Boolean) -> Unit = { isShow ->
            coroutineScope.launch {
                shimmerVisibility = isShow && recipes.itemSnapshotList.size == 0
            }
        }

        val showSnackBar: (
            message: String?,
            actionLabel: String,
            actionPerformed: () -> Unit,
            dismissed: () -> Unit
        ) -> Unit = { message, actionLabel, actionPerformed, dismissed ->
            coroutineScope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = message.toString(),
                    actionLabel = actionLabel
                )
                when (snackBarResult) {
                    SnackbarResult.ActionPerformed -> actionPerformed.invoke()
                    SnackbarResult.Dismissed -> dismissed.invoke()
                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SearchBar(
                    viewModel = viewModel,
                    onSearchClicked = {
                        refreshPage.invoke()
                    }
                )
            },
            content = {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    //create reference for the views to constraint
                    val (chips, contents, shimmer) = createRefs()

                    LazyRow(
                        modifier = Modifier
                            .semantics { contentDescription = "chips" }
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
                                lazyListState = lazyRowState,
                                onChipClicked = { text ->
                                    viewModel.onSelectedCategory(text)
                                    refreshPage.invoke()
                                }
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

                    AnimatedVisibility(
                        visible = shimmerVisibility,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(shimmer) {
                                top.linkTo(chips.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                height = Dimension.fillToConstraints
                            },
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        LazyColumn {
                            repeat(10) {
                                item {
                                    ShimmerAnimationBrush {
                                        ShimmerRecipeCard(
                                            brush = it
                                        )
                                    }

                                }
                            }
                        }
                    }

                    LazyColumn(
                        state = lazyColumnState,
                        modifier = Modifier
                            .semantics { contentDescription = "search items" }
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
                            RecipeCard(
                                recipe = recipe!!,
                                onClick = {
                                    navController.navigate(
                                        "${Screen.Detail.route}/${recipe.id}"
                                    )
                                }
                            )
                        }

                        recipes.apply {
                            when {
                                loadState.append is LoadState.Loading -> {
                                    item {
                                        LoadingListItem()
                                    }
                                }

                                loadState.append is LoadState.Error -> {
                                    viewModel.setLoading(false)
                                    item {
                                        ErrorListItem(
                                            error = (loadState.append as LoadState.Error).error.message,
                                            onTryClicked = {
                                                retry()
                                            }
                                        )
                                    }
                                }

                                loadState.refresh is LoadState.Loading -> {
                                    viewModel.setLoading(true)
                                    changeShimmerVisibility.invoke(true)
                                }

                                loadState.refresh is LoadState.Error -> {
                                    viewModel.setLoading(false)
                                    showSnackBar.invoke(
                                        (loadState.refresh as LoadState.Error).error.message,
                                        "Try again",
                                        {
                                            //on action performed
                                            refresh()
                                        }, {
                                            //on dismissed
                                        }
                                    )
                                }

                                //this must check last
                                loadState.refresh is LoadState.NotLoading -> {
                                    viewModel.setLoading(false)
                                    changeShimmerVisibility.invoke(false)
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}