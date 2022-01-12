package com.jetpackcompose.core.model

sealed class NetworkDataState<out T> {
    class Success<T>(val data: T) : NetworkDataState<T>()
    class Error(val networkStatus: NetworkStatus) : NetworkDataState<Nothing>()
}