package com.jetpackcompose.app.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.ui.home.components.RecipeCard
import com.jetpackcompose.app.presentation.ui.home.components.SearchBar

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = { SearchBar(viewModel) }
    ) {

    }
    Column {
        SearchBar(viewModel = viewModel)
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(viewModel.recipeList.value) { recipe ->
                RecipeCard(recipe = recipe, onClick = {})
            }
        }
    }
}