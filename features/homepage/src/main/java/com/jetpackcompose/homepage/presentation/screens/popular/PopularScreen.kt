package com.jetpackcompose.homepage.presentation.screens.popular

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.homepage.presentation.components.RecipeLargeCard
import com.jetpackcompose.resources.theme.textColor

@ExperimentalCoilApi
@Composable
fun PopularScreen(
    navController: NavController,
    viewModel: PopularScreenViewModel = hiltViewModel()
) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "popular items" }
    ) {
        items(viewModel.popularList.value) {
            RecipeLargeCard(
                recipe = it,
                onClick = {
                    navController.navigate(
                        "${Screen.Detail.route}/${it.id}"
                    )
                }
            )
        }
    }
}