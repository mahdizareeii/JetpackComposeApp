package com.jetpackcompose.homepage.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jetpackcompose.homepage.presentation.HomeScreenViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: HomeScreenViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Row(
            Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.query.value,
                textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
                onValueChange = { text ->
                    viewModel.inValidateSelectedCategory()
                    viewModel.onQueryChanged(text)
                },
                label = { Text(text = "Search") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.searchFood()
                        keyboardController?.hide()
                    }
                ),
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "search icon") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.background)
            )
        }
    }
}