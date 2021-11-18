package com.jetpackcompose.domain.util.model

import com.jetpackcompose.domain.util.network.calladapter.NetworkStatus

sealed class UiDataState<out T> {
    class Success<T>(val data: T) : UiDataState<T>()
    class Error(val networkStatus: NetworkStatus) : UiDataState<Nothing>()
    class Loading(val isLoading: Boolean) : UiDataState<Nothing>()
}