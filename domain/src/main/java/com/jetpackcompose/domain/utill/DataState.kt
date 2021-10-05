package com.jetpackcompose.domain.utill

import com.jetpackcompose.domain.utill.network.calladapter.NetworkStatus

sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val networkStatus: NetworkStatus) : DataState<Nothing>()
}
