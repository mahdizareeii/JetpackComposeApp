package com.jetpackcompose.homepage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreen
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreenViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@RunWith(AndroidJUnit4::class)
class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var controller: NavHostController

    private lateinit var viewModel: SearchScreenViewModel

    @Mock
    private val searchRecipesUseCase = Mockito.mock(SearchRecipesUseCase::class.java)


    @Before
    fun setup() {
        runBlocking {
            Mockito.`when`(searchRecipesUseCase.invoke(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(
                    NetworkDataState.Success(
                        listOf(
                            Recipe(id = 1, title = "sample1"),
                            Recipe(id = 2, title = "sample2"),
                            Recipe(id = 3, title = "sample3"),
                        )
                    )
                )

            viewModel = SearchScreenViewModel(
                searchRecipesUseCase
            )

            composeTestRule.setContent {
                controller = rememberNavController()
                SearchScreen(controller, viewModel)
            }
        }
    }

    @Test
    fun chipsPerformItemClick() {
        composeTestRule
            .onNodeWithContentDescription("chips")
            .onChildAt(1)
            .performClick()
    }

    @Test
    fun performSwipeForChips() {
        composeTestRule
            .onNodeWithContentDescription("chips")
            .performTouchInput {
                left
                right
            }
    }

    @Test
    fun performClickOnSearchItems() {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {
                Screen.HomeSearch.route -> {
                    composeTestRule
                        .onNodeWithContentDescription("search items")
                        .onChildAt(1)
                        .performClick()
                }
            }
        }
    }
}