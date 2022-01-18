package com.jetpackcompose.detailpage

import androidx.lifecycle.SavedStateHandle
import com.jetpackcompose.core.model.UiDataState
import com.jetpackcompose.detailpage.presentation.DetailScreenViewModel
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.GetRecipeByIdUseCase
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailScreenViewModelTest : TestCase() {

    private lateinit var viewModel: DetailScreenViewModel

    @Mock
    private val getRecipeByIdUseCase = mock(GetRecipeByIdUseCase::class.java)

    private val savedStateHandle = SavedStateHandle()

    private val recipeId = 1

    @Before
    fun setup() {
        savedStateHandle.set("id", recipeId.toString())
    }

    @Test
    fun `get recipe by id should return a flow of data`() {
        CoroutineScope(Dispatchers.IO).launch {
            `when`(getRecipeByIdUseCase.invoke(anyInt())).thenReturn(
                getFlowOfRecipeData()
            )

            viewModel = DetailScreenViewModel(
                getRecipeByIdUseCase = getRecipeByIdUseCase,
                savedStateHandle = savedStateHandle
            )

            verify(getRecipeByIdUseCase.invoke(recipeId)).onEach {
                assertEquals(
                    (it as UiDataState.Success).data, Recipe(
                        id = recipeId,
                        title = "test recipe"
                    )
                )
            }
        }
    }

    private fun getFlowOfRecipeData() = flowOf(
        UiDataState.Success(getTestRecipe())
    )

    private fun getTestRecipe() = Recipe(
        id = recipeId,
        title = "test recipe"
    )

}