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
import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.GetPopularRecipesUseCase
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreen
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreenViewModel
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreen
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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

    private lateinit var searchScreenViewModel: SearchScreenViewModel
    private lateinit var popularScreenViewModel: PopularScreenViewModel
    private lateinit var controller: NavHostController

    @Mock
    private val searchRecipesUseCase = mock(SearchRecipesUseCase::class.java)

    @Mock
    private val popularRecipesUseCase = mock(GetPopularRecipesUseCase::class.java)

    @Before
    fun setup() {
        runBlocking {
            `when`(searchRecipesUseCase.invoke(anyInt(), anyString())).thenReturn(
                NetworkDataState.Success(
                    listOf(
                        Recipe(id = 1, title = "sample1"),
                        Recipe(id = 2, title = "sample2"),
                        Recipe(id = 3, title = "sample3"),
                    )
                )
            )

            `when`(popularRecipesUseCase.invoke()).thenReturn(
                NetworkDataState.Success(
                    listOf(
                        Recipe(id = 1, title = "sample1"),
                        Recipe(id = 2, title = "sample2"),
                        Recipe(id = 3, title = "sample3"),
                    )
                )
            )

            searchScreenViewModel = SearchScreenViewModel(
                searchRecipesUseCase
            )

            popularScreenViewModel = PopularScreenViewModel(
                popularRecipesUseCase
            )
        }
    }

    @Test
    fun popularScreenIsShow() {
        composeTestRule.setContent {
            controller = rememberNavController()
            PopularScreen(controller, popularScreenViewModel)
        }
        composeTestRule
            .onNodeWithContentDescription("popular items")
            .assertIsDisplayed()
    }

    @Test
    fun chipsPerformItemClick() {
        composeTestRule.setContent {
            controller = rememberNavController()
            SearchScreen(controller, searchScreenViewModel)
        }
        composeTestRule
            .onNodeWithContentDescription("chips")
            .onChildAt(1)
            .performClick()
    }

    @Test
    fun performSwipeForChips() {
        composeTestRule.setContent {
            controller = rememberNavController()
            SearchScreen(controller, searchScreenViewModel)
        }
        composeTestRule
            .onNodeWithContentDescription("chips")
            .performTouchInput {
                left
                right
            }
    }

    @Test
    fun performClickOnSearchItems() {
        composeTestRule.setContent {
            controller = rememberNavController()
            SearchScreen(controller, searchScreenViewModel)
        }
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