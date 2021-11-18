package com.jetpackcompose.app.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.ui.home.components.Chip
import com.jetpackcompose.app.presentation.ui.home.components.RecipeCard
import com.jetpackcompose.app.presentation.ui.home.components.SearchBar
import com.jetpackcompose.domain.model.FoodCategory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

@ExperimentalCoilApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val errorMessage = viewModel.error.value
    val loading = viewModel.loading.value

    val channel = getCoroutineChannel()
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

    errorMessage.takeIf { it.isNotEmpty() }?.let {
        channel.trySend(it)
    }

    loading.takeIf { it }?.let {
        channel.trySend("getting data")
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(FoodCategory.getAllFood()) {
                        Chip(
                            text = it.foodName,
                            viewModel = viewModel
                        )
                    }
                }

                LazyColumn {
                    items(viewModel.recipeList.value) { recipe ->
                        RecipeCard(recipe = recipe, onClick = {})
                    }
                }
            }
        }
    )
}

@Composable
fun getSnackBarHostState(
    channel: Channel<String>,
    result: (SnackbarResult) -> Unit
): SnackbarHostState {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(channel) {
        channel.receiveAsFlow().collect { message ->
            result.invoke(
                snackBarHostState.showSnackbar(
                    message = message,
                    actionLabel = "close"
                )
            )
        }
    }
    return snackBarHostState
}

@Composable
fun getCoroutineChannel() = remember { Channel<String>(Channel.Factory.CONFLATED) }