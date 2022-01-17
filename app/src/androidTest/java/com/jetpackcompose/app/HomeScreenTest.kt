package com.jetpackcompose.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.navigation.Navigation
import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.homepage.presentation.HomeScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@ExperimentalTestApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var controller: NavHostController

    @Mock
    private val searchRecipesUseCase = mock(SearchRecipesUseCase::class.java)

    @Before
    fun setup() {
        runBlocking {
            composeTestRule.setContent {
                controller = rememberNavController()
                Navigation(controller)
            }

            `when`(searchRecipesUseCase.invoke(anyInt(), anyString())).thenReturn(
                NetworkDataState.Success(
                    listOf(
                        Recipe(id = 1, title = "sample1"),
                        Recipe(id = 2, title = "sample2"),
                        Recipe(id = 3, title = "sample3"),
                    )
                )
            )

            viewModel = HomeScreenViewModel(
                searchRecipesUseCase
            )
        }
    }

    @Test
    fun chipsPerformItemClick() {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {
                Screen.HomeScreen.route -> {
                    composeTestRule
                        .onNodeWithContentDescription("chips")
                        .onChildAt(1)
                        .performClick()
                }
            }
        }

    }

    @Test
    fun performSwipeForChips() {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {
                Screen.HomeScreen.route -> {
                    composeTestRule
                        .onNodeWithContentDescription("chips")
                        .performGesture {
                            left
                            right
                        }
                }
            }
        }
    }

    @Test
    fun performClickOnDetailItems() {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {
                Screen.HomeScreen.route -> {
                    composeTestRule
                        .onNodeWithContentDescription("home items")
                        .onChildAt(1)
                        .performClick()
                }
            }
        }
    }

    @Test
    fun detailScreenIsShow() {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {
                Screen.DetailScreen.route -> {
                    composeTestRule
                        .onNodeWithContentDescription("detail")
                        .assertIsDisplayed()
                }
            }
        }
    }
}