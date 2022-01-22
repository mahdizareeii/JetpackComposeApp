package com.jetpackcompose.homepage.presentation.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jetpackcompose.domain.model.FoodCategory
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.pagingsource.RecipePagingSource
import com.jetpackcompose.domain.usecase.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    private val size = 10
    val recipeList: Flow<PagingData<Recipe>> = Pager(
        config = PagingConfig(
            pageSize = size,
            initialLoadSize = size,
            prefetchDistance = size / 3,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            RecipePagingSource(searchRecipesUseCase, query.value)
        }
    ).flow.cachedIn(viewModelScope)

    private val _loading: MutableState<Boolean> = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _selectedCategory: MutableState<FoodCategory> = mutableStateOf(FoodCategory.UN_KNOW)
    val selectedCategory: State<FoodCategory> get() = _selectedCategory

    //save scroll position state of the lazy row in home screen
    var lazyRowScrollIndexPosition = 0
    var lazyRowScrollOffsetPosition = 0

    fun onQueryChanged(text: String) {
        _query.value = text
    }

    fun onSelectedCategory(category: String) {
        _selectedCategory.value = FoodCategory.getFoodCategory(category)
        onQueryChanged(category)
    }

    fun inValidateSelectedCategory() {
        _selectedCategory.value = FoodCategory.UN_KNOW
    }

    fun setLoading(value:Boolean){
        _loading.value = value
    }
}