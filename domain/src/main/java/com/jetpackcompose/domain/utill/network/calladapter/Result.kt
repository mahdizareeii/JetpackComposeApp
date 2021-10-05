package com.jetpackcompose.domain.utill.network.calladapter

import com.jetpackcompose.domain.utill.network.calladapter.util.StatusCode

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val statusCode: StatusCode) : Result<Nothing>()
}
