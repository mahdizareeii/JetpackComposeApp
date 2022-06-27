package com.jetpackcompose.detailpage

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.core.model.UiDataState
import com.jetpackcompose.core.util.navigation.Screen
import com.jetpackcompose.detailpage.presentation.DetailScreen
import com.jetpackcompose.detailpage.presentation.DetailScreenViewModel
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.GetRecipeByIdUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var controller: NavHostController

    private lateinit var viewModel: DetailScreenViewModel

    private val savedStateHandle = SavedStateHandle()

    @Mock
    private val getRecipeByIdUseCase = mock(GetRecipeByIdUseCase::class.java)

    private val recipeId = 1

    @Before
    fun setup() {
        runBlocking {
            savedStateHandle.set("id", recipeId.toString())

            `when`(getRecipeByIdUseCase.invoke(anyInt())).thenReturn(
                flowOf(
                    UiDataState.Loading(true),
                    UiDataState.Success(
                        Recipe(id = recipeId, title = "sample1")
                    ),
                    UiDataState.Loading(false)
                )
            )

            viewModel = DetailScreenViewModel(
                getRecipeByIdUseCase = getRecipeByIdUseCase,
                savedStateHandle = savedStateHandle
            )

            composeTestRule.setContent {
                controller = rememberNavController()
                DetailScreen()
            }
        }
    }

    @Test
    fun detailScreenIsShow() {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {
                Screen.Detail.route -> {
                    composeTestRule
                        .onNodeWithContentDescription("detail")
                        .assertIsDisplayed()
                }
            }
        }
    }
}