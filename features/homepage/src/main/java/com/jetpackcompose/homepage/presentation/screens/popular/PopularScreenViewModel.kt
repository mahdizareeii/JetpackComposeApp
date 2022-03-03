package com.jetpackcompose.homepage.presentation.screens.popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.core.model.UiDataState
import com.jetpackcompose.domain.model.PopularScreenUIState
import com.jetpackcompose.domain.usecase.GetPopularRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularScreenViewModel @Inject constructor(
    private val getPopularRecipesUseCase: GetPopularRecipesUseCase
) : ViewModel() {

    private val _popularList: MutableState<List<PopularScreenUIState>> = mutableStateOf(listOf())
    val popularList: State<List<PopularScreenUIState>> get() = _popularList

    private val _loading: MutableState<Boolean> = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> get() = _error

    init {
        getPopularList()
    }

    fun getPopularList() {
        viewModelScope.launch {
            _loading.value = true
            when (val result = getPopularRecipesUseCase.invoke()) {
                is UiDataState.Success -> _popularList.value = result.data
                is UiDataState.Error -> _error.value = result.networkStatus.message
            }
            _loading.value = false
        }
    }

}