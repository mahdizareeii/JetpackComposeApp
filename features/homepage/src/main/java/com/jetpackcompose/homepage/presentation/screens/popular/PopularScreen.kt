package com.jetpackcompose.homepage.presentation.screens.popular

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.domain.model.PopularScreenUIState
import com.jetpackcompose.homepage.presentation.components.RecipeCheapProduct
import com.jetpackcompose.homepage.presentation.components.RecipeMostSell

@ExperimentalCoilApi
@Composable
fun PopularScreen(
    navController: NavController,
    viewModel: PopularScreenViewModel = hiltViewModel()
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
            }

        }
    }
}