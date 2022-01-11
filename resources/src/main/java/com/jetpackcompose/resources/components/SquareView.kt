package com.jetpackcompose.resources.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun SquareView(modifier: Modifier, content: @Composable() () -> Unit) {
    Layout(content = content, modifier = modifier) { measurableList, constraints ->
        layout(constraints.maxWidth, constraints.maxWidth) {
            val childConstraint = constraints.copy(
                minWidth = constraints.maxWidth,
                maxWidth = constraints.maxWidth,
                minHeight = constraints.maxWidth,
                maxHeight = constraints.maxWidth
            )
            require(measurableList.size == 1)
            measurableList[0].measure(childConstraint).place(0, 0)
        }

    }
}