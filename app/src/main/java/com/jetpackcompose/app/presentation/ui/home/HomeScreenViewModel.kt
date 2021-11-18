package com.jetpackcompose.app.presentation.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.domain.model.FoodCategory
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import com.jetpackcompose.domain.util.model.UiDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    private val _loading: MutableState<Boolean> = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _error: MutableState<String> = mutableStateOf("")
    val error: State<String> get() = _error

    private val _recipeList: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val recipeList: State<List<Recipe>> get() = _recipeList

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _selectedCategory: MutableState<FoodCategory> = mutableStateOf(FoodCategory.UN_KNOW)
    val selectedCategory: State<FoodCategory> get() = _selectedCategory

    fun onQueryChanged(text: String) {
        _query.value = text
    }

    fun onSelectedCategory(category: String) {
        _selectedCategory.value = FoodCategory.getFoodCategory(category)
        onQueryChanged(category)
        searchFood()
    }

    fun inValidateSelectedCategory() {
        _selectedCategory.value = FoodCategory.UN_KNOW
    }

    fun searchFood() {
        searchRecipesUseCase(page = 1, query.value).onEach {
            when (it) {
                is UiDataState.Success -> {
                    _recipeList.value = it.data
                }
                is UiDataState.Error -> it.networkStatus.message
                is UiDataState.Loading -> _loading.value = it.isLoading
            }
        }.launchIn(viewModelScope)
    }
}