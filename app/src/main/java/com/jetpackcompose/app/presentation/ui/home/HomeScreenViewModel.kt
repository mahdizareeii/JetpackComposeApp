package com.jetpackcompose.app.presentation.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    @Named("token") private val token: String
) : ViewModel() {

    private val _recipeList: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val recipeList: State<List<Recipe>> get() = _recipeList

    init {
        searchRecipe()
    }

    private fun searchRecipe() {
        viewModelScope.launch {
            val result = searchRecipesUseCase.execute(page = 1, "chicken", token = token)
            _recipeList.value = result
        }
    }
}