package com.jetpackcompose.app.presentation.theme.training

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcompose.app.R
import com.jetpackcompose.app.presentation.theme.AppTheme

@Composable
fun SampleItem() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        items(1) {
            Image(
                painter = painterResource(id = R.drawable.food),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "food image"
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Happy Meal",
                        style = TextStyle(
                            fontSize = 26.sp,
                            color = MaterialTheme.colors.secondary
                        )
                    )

                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "$5.99",
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = MaterialTheme.colors.onSecondary
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(top = 8.dp))

                Text(
                    text = "800 calories",
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = MaterialTheme.colors.onSecondary
                    )
                )

                Spacer(modifier = Modifier.padding(top = 8.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.background
                    )
                ) {
                    Text(text = "Order Now")
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewRecipeFood() {
    AppTheme {
        SampleItem()
    }
}