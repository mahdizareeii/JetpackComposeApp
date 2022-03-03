package com.jetpackcompose.homepage

import com.jetpackcompose.core.model.UiDataState
import com.jetpackcompose.domain.model.PopularScreenUIState
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.GetPopularRecipesUseCase
import com.jetpackcompose.homepage.presentation.screens.popular.PopularScreenViewModel
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularScreenViewModelTest : TestCase() {

    private lateinit var popularScreenViewModel: PopularScreenViewModel

    @Mock
    private val getPopularRecipesUseCase = mock(GetPopularRecipesUseCase::class.java)

    @Before
    fun setup(){

    }

    @Test
    fun `get popular recipes should return a list of popular`() {
        CoroutineScope(Dispatchers.Default).launch {
            `when`(getPopularRecipesUseCase.invoke()).thenReturn(getListOfPopulars())

            popularScreenViewModel = PopularScreenViewModel(
                getPopularRecipesUseCase = getPopularRecipesUseCase
            )

            verify(getPopularRecipesUseCase).invoke()
        }
    }

    private fun getListOfPopulars() = UiDataState.Success(
        listOf(
            PopularScreenUIState.MostSells(
                listOf(
                    Recipe(
                        id = 1,
                        title = "test 1"
                    )
                )
            ),
            PopularScreenUIState.CheapProducts(
                listOf(
                    Recipe(
                        id = 1,
                        title = "test 1"
                    )
                )
            )
        )
    )

}