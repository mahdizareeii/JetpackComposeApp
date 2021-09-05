package com.jetpackcompose.app.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val sampleText: String
) : ViewModel() {
    init {
        Log.i("test",sampleText)
    }
}