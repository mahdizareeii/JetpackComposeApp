package com.jetpackcompose.app.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.app.domain.model.Recipe
import com.jetpackcompose.app.domain.usecase.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    @Named("token") private val token: String
) : ViewModel() {

    private val _recipeList: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val recipeList: State<List<Recipe>> get() = _recipeList

    init {
        viewModelScope.launch {
            val result = searchRecipesUseCase.execute(page = 1, "chicken", token = token)
            _recipeList.value = result
        }
    }
}