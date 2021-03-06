package com.jetpackcompose.homepage

import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.homepage.presentation.screens.search.SearchScreenViewModel
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
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
class SearchViewModelTest : TestCase() {

    private lateinit var viewModel: SearchScreenViewModel

    @Mock
    private val searchRecipesUseCase = mock(SearchRecipesUseCase::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun `on selected category should init query`() {
        viewModel = getViewModel()
        viewModel.onSelectedCategory("test")
        assertEquals("test", viewModel.query.value)
    }

    @Test
    fun `search recipe should return a list of recipe`() {
        CoroutineScope(Dispatchers.Default).launch {
            `when`(searchRecipesUseCase.invoke(anyInt(), anyString()))
                .thenReturn(getListOfRecipe())

            viewModel = getViewModel()

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

    private fun getViewModel() = SearchScreenViewModel(
        searchRecipesUseCase = searchRecipesUseCase
    )

}