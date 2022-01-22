package com.jetpackcompose.detailpage.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.core.model.UiDataState
import com.jetpackcompose.domain.model.Recipe
import com.jetpackcompose.domain.usecase.GetRecipeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detail: MutableState<Recipe?> = mutableStateOf(null)
    val detail: State<Recipe?> get() = _detail

    private val _loading: MutableState<Boolean> = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> get() = _error

    private val id get() = savedStateHandle.get<String>("id")?.toIntOrNull() ?: 0

    init {
        getRecipeById()
    }

    fun getRecipeById() {
        clearError()
        viewModelScope.launch {
            getRecipeByIdUseCase.invoke(id)
                .onEach {
                    when (it) {
                        is UiDataState.Success -> {
                            _detail.value = it.data
                        }

                        is UiDataState.Error -> {
                            _error.value = it.networkStatus.message
                        }

                        is UiDataState.Loading -> {
                            _loading.value = it.isLoading
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun clearError() {
        _error.value = null
    }
}