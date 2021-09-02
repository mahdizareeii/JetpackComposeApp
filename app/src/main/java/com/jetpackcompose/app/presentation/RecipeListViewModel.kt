package com.jetpackcompose.app.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val sampleText: String
) : ViewModel() {
    init {
        Log.i("123123123",sampleText)
    }
}