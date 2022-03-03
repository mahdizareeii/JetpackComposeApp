package com.jetpackcompose.homepage.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreenViewModel
import com.jetpackcompose.resources.components.CircularProgress
import com.jetpackcompose.resources.theme.textColor

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    viewModel: SearchScreenViewModel,
    onSearchClicked: () -> Unit
) {
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
                textStyle = TextStyle(color = MaterialTheme.colors.textColor),
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
                        onSearchClicked.invoke()
                        keyboardController?.hide()
                    }
                ),
                leadingIcon = {
                    if (viewModel.loading.value)
                        CircularProgress(
                            Modifier
                                .width(25.dp)
                                .height(25.dp)
                        )
                    else
                        Icon(Icons.Filled.Search, contentDescription = "search icon")
                },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.background)
            )
        }
    }
}