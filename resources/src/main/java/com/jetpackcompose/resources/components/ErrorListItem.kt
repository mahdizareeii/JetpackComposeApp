package com.jetpackcompose.resources.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jetpackcompose.resources.theme.textColor

@Composable
fun ErrorListItem(error: String?, onTryClicked: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
    ) {
        val (message, retry) = createRefs()

        Text(
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(message) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(retry.start)

                    width = Dimension.fillToConstraints
                },
            text = error ?: "",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.body1
        )
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(retry) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            onClick = onTryClicked
        ) {
            Text(
                text = "Retry",
                color = MaterialTheme.colors.textColor,
                style = MaterialTheme.typography.button
            )
        }
    }
}