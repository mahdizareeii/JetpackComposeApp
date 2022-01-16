package com.jetpackcompose.homepage

import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.homepage.presentation.HomeScreenViewModel
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeScreenViewModelTest : TestCase() {

    private lateinit var viewModel: HomeScreenViewModel

    @Mock
    private val searchRecipesUseCase = mock(SearchRecipesUseCase::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun `search recipe should return a list of recipe`() {
        CoroutineScope(Dispatchers.IO).launch {
            `when`(searchRecipesUseCase.invoke(anyInt(), anyString()))
                .thenReturn(getListOfRecipe())

            viewModel = HomeScreenViewModel(
                searchRecipesUseCase = searchRecipesUseCase
            )

            verify(searchRecipesUseCase).invoke(anyInt(), anyString())
        }
    }

    private fun getListOfRecipe() = NetworkDataState.Success(
        listOf(
            Recipe(
                id = 1,
                title = "recipe 1"
            ),
            Recipe(
                id = 2,
                title = "recipe 2"
            ),
            Recipe(
                id = 3,
                title = "recipe 3"
            )
        )
    )

}