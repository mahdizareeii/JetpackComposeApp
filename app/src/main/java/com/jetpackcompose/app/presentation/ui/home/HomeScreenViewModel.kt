package com.jetpackcompose.app.presentation.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.domain.utill.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    private val _error: MutableState<String> = mutableStateOf("")
    val error: State<String> get() = _error

    private val _recipeList: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val recipeList: State<List<Recipe>> get() = _recipeList

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    init {

    }

    fun onQueryChanged(text: String) {
        _query.value = text
    }

    fun searchFood() {
        viewModelScope.launch {
            when (val result = searchRecipesUseCase(page = 1, query.value)) {
                is DataState.Success -> _recipeList.value = result.data
                is DataState.Error -> {
                    _error.value = ""
                    _error.value = result.networkStatus.message
                }
            }
        }
    }
}