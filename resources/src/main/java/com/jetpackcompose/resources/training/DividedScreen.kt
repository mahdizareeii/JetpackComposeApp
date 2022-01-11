package com.jetpackcompose.resources.training

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout


/*
how to use:

DividedScreen(modifier = Modifier.fillMaxSize()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Magenta)
    ) {}
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
    ) {}
}

this sample divide screen into two split
*/

@Composable
fun DividedScreen(modifier: Modifier, content: @Composable() () -> Unit) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val halfHeight = constraints.maxHeight / 2
            val childConstraint = constraints.copy(
                minHeight = minOf(constraints.minHeight, halfHeight),
                maxHeight = halfHeight
            )
            require(measurables.size == 2)
            measurables[0].measure(childConstraint).place(0, 0)
            measurables[1].measure(childConstraint).place(0, halfHeight)
        }
    }
}