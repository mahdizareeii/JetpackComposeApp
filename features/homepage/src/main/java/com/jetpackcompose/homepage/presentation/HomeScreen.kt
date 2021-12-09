package com.jetpackcompose.homepage.presentation

import androidx.compose.foundation.layout.Column
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.domain.model.FoodCategory
import com.jetpackcompose.domain.util.compose.getSnackBarCoroutineChannel
import com.jetpackcompose.domain.util.compose.getSnackBarHostState
import com.jetpackcompose.homepage.presentation.components.Chip
import com.jetpackcompose.homepage.presentation.components.RecipeCard
import com.jetpackcompose.homepage.presentation.components.SearchBar
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val errorMessage = viewModel.error.value
    val loading = viewModel.loading.value

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

    errorMessage.takeIf { it.isNotEmpty() }?.let {
        channel.trySend(it)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SearchBar(viewModel = viewModel)
        },
        content = {
            Column {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(viewModel.recipeList.value) { recipe ->
                        RecipeCard(recipe = recipe, onClick = {})
                    }
                }
            }
        }
    )
}