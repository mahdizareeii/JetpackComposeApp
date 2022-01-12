package com.jetpackcompose.core.model

sealed class UiDataState<out T> {
    class Success<T>(val data: T) : UiDataState<T>()
    class Error(val networkStatus: NetworkStatus) : UiDataState<Nothing>()
    class Loading(val isLoading: Boolean) : UiDataState<Nothing>()
}