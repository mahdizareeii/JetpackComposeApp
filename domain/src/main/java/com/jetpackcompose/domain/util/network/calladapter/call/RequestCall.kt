package com.jetpackcompose.domain.util.network.calladapter.call

import com.jetpackcompose.domain.util.model.NetworkDataState
import com.jetpackcompose.domain.util.network.calladapter.NetworkStatus
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RequestCall<T>(proxy: Call<T>) : CallDelegate<T, NetworkDataState<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<NetworkDataState<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                val body = response.body()

                val result = if (code in 200 until 300) {
                    if (body != null)
                        NetworkDataState.Success(body)
                    else
                        NetworkDataState.Error(NetworkStatus.ResponseBodyIsNull)
                } else {
                    NetworkDataState.Error(NetworkStatus.fromStatusCode(code))
                }

                callback.onResponse(this@RequestCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = when (t) {
                    is IOException -> {
                        NetworkDataState.Error(NetworkStatus.NetworkError)
                    }
                    is HttpException -> {
                        NetworkDataState.Error(NetworkStatus.fromStatusCode(t.code()))
                    }
                    else -> NetworkDataState.Error(NetworkStatus.Unknown)
                }
                callback.onResponse(this@RequestCall, Response.success(result))
            }
        })
    }

    override fun cloneImpl(): Call<NetworkDataState<T>> = RequestCall(proxy.clone())

    override fun timeout(): Timeout = proxy.timeout()
}