package com.jetpackcompose.app.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.ui.home.components.Chip
import com.jetpackcompose.app.presentation.ui.home.components.RecipeCard
import com.jetpackcompose.app.presentation.ui.home.components.SearchBar
import com.jetpackcompose.app.presentation.ui.home.util.FoodCategory

@ExperimentalCoilApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SearchBar(viewModel = viewModel)
        }
    ) {
        Column {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(FoodCategory.getAllFood()) {
                    Chip(text = it.foodName)
                }
            }

            LazyColumn {
                items(viewModel.recipeList.value) { recipe ->
                    RecipeCard(recipe = recipe, onClick = {})
                }
            }
        }
    }
}