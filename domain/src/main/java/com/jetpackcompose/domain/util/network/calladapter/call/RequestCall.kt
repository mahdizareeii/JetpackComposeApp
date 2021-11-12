package com.jetpackcompose.domain.util.network.calladapter.call

import com.jetpackcompose.domain.util.DataState
import com.jetpackcompose.domain.util.network.calladapter.NetworkStatus
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RequestCall<T>(proxy: Call<T>) : CallDelegate<T, DataState<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<DataState<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                val body = response.body()

                val result = if (code in 200 until 300) {
                    if (body != null)
                        DataState.Success(body)
                    else
                        DataState.Error(NetworkStatus.ResponseBodyIsNull)
                } else {
                    DataState.Error(NetworkStatus.fromStatusCode(code))
                }

                callback.onResponse(this@RequestCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = when (t) {
                    is IOException -> {
                        DataState.Error(NetworkStatus.NetworkError)
                    }
                    is HttpException -> {
                        DataState.Error(NetworkStatus.fromStatusCode(t.code()))
                    }
                    else -> DataState.Error(NetworkStatus.Unknown)
                }
                callback.onResponse(this@RequestCall, Response.success(result))
            }
        })
    }

    override fun cloneImpl(): Call<DataState<T>> = RequestCall(proxy.clone())

    override fun timeout(): Timeout = proxy.timeout()
}