package com.jetpackcompose.app.presentation.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jetpackcompose.app.presentation.ui.home.components.RecipeCard

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    LazyColumn {
        items(viewModel.recipeList.value) { recipe ->
            RecipeCard(recipe = recipe, onClick = {})
        }
    }
}