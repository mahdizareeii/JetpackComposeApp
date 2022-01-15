package com.jetpackcompose.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.homepage.presentation.HomeScreenViewModel
import com.jetpackcompose.homepage.presentation.MainScreen
import com.jetpackcompose.resources.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @Mock
    private val searchRecipesUseCase = mock(SearchRecipesUseCase::class.java)

    @Before
    fun setup() {
        runBlockingTest {
            `when`(searchRecipesUseCase.invoke(anyInt(), anyString())).thenReturn(
                NetworkDataState.Success(listOf(
                    Recipe(id = 1),
                    Recipe(id = 2),
                    Recipe(id = 3),
                ))
            )

            viewModel = HomeScreenViewModel(
                searchRecipesUseCase
            )
        }
    }

    @Test
    fun chipsPerformItemClick() {
        composeTestRule.setContent {
            AppTheme {
                MainScreen(
                    navController = rememberNavController(),
                    viewModel = viewModel
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("chips")
            .onChildAt(1)
            .performClick()

    }

}