package com.jetpackcompose.homepage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.model.UiDataState
import com.jetpackcompose.domain.model.PopularScreenUIState
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.GetPopularRecipesUseCase
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreen
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreenViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalAnimationApi
@ExperimentalCoilApi
@RunWith(AndroidJUnit4::class)
class PopularScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var controller: NavHostController

    private lateinit var viewModel: PopularScreenViewModel

    @Mock
    private val getPopularRecipesUseCase = mock(GetPopularRecipesUseCase::class.java)

    @Before
    fun setup() {
        runBlocking {
            Mockito.`when`(getPopularRecipesUseCase.invoke()).thenReturn(
                UiDataState.Success(
                    listOf(
                        PopularScreenUIState.CheapProducts(
                            listOf(
                                Recipe(id = 2, title = "sample2")
                            )
                        ),
                        PopularScreenUIState.MostSells(
                            listOf(
                                Recipe(id = 2, title = "sample2")
                            )
                        )
                    )
                )
            )

            viewModel = PopularScreenViewModel(
                getPopularRecipesUseCase
            )

            composeTestRule.setContent {
                controller = rememberNavController()
                PopularScreen(controller, viewModel)
            }
        }
    }

    @Test
    fun popularScreenIsShow() {
        composeTestRule
            .onNodeWithContentDescription("popular items")
            .assertIsDisplayed()
    }
}