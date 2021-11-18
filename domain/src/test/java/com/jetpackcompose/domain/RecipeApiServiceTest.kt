package com.jetpackcompose.domain

import com.jetpackcompose.domain.network.api.RecipeApiService
import com.jetpackcompose.domain.network.model.RecipeDto
import com.jetpackcompose.domain.util.model.NetworkDataState
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeApiServiceTest : TestCase() {

    @Mock
    private val apiService = mock(RecipeApiService::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun `get recipe by id should return empty object`() {
        runBlocking {
            val fakeData = getFakeDataOfRecipe()
            `when`(apiService.getRecipeById(1)).thenReturn(fakeData)
            val recipe = apiService.getRecipeById(1)
            assertEquals(fakeData, recipe)
        }
    }

    private fun getFakeDataOfRecipe() = NetworkDataState.Success(RecipeDto())

}