package com.jetpackcompose.domain.util.model

import com.jetpackcompose.domain.util.network.calladapter.NetworkStatus

sealed class NetworkDataState<out T> {
    class Success<T>(val data: T) : NetworkDataState<T>()
    class Error(val networkStatus: NetworkStatus) : NetworkDataState<Nothing>()
}