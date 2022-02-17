package com.jetpackcompose.detailpage

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.detailpage.presentation.DetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var controller: NavHostController

    @Test
    fun detailScreenIsShow() {
        composeTestRule.setContent {
            controller = rememberNavController()
            DetailScreen(argument = Bundle.EMPTY)
        }
        composeTestRule
            .onNodeWithContentDescription("detail")
            .assertIsDisplayed()
    }
}