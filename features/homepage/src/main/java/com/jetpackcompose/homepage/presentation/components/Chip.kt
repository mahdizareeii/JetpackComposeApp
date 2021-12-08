package com.jetpackcompose.homepage.presentation.components

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackcompose.homepage.presentation.HomeScreenViewModel

@RequiresApi(Build.VERSION_CODES.FROYO)
@Preview(name = "Light Mode")
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewChip() {
    MaterialTheme {
        Chip(text = "Test", viewModel = viewModel(), lazyListState = LazyListState())
    }
}

@Composable
fun Chip(
    text: String,
    viewModel: HomeScreenViewModel,
    lazyListState: LazyListState
) {
    val isSelected = viewModel.selectedCategory.value.foodName == text

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .toggleable(
                value = isSelected,
                onValueChange = {
                    viewModel.onSelectedCategory(text)

                    //for save position of lazy row with click on chips
                    viewModel.lazyRowStatePosition = lazyListState.firstVisibleItemIndex
                    viewModel.lazyRowScrollOffsetPosition = lazyListState.firstVisibleItemScrollOffset
                }
            ),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.caption
        )
    }
}