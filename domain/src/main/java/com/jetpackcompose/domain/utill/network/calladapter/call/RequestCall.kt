package com.jetpackcompose.domain.utill.network.calladapter.call

import com.jetpackcompose.domain.utill.network.calladapter.Result
import com.jetpackcompose.domain.utill.network.calladapter.util.StatusCode
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RequestCall<T>(proxy: Call<T>) : CallDelegate<T, Result<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<Result<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                val body = response.body()

                val result = if (code in 200 until 300) {
                    if (body != null)
                        Result.Success(body)
                    else
                        Result.Error(StatusCode.ResponseBodyIsNull)
                } else {
                    Result.Error(StatusCode.fromStatusCode(code))
                }

                callback.onResponse(this@RequestCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = when (t) {
                    is IOException -> {
                        Result.Error(StatusCode.NetworkError)
                    }
                    is HttpException -> {
                        Result.Error(StatusCode.fromStatusCode(t.code()))
                    }
                    else -> Result.Error(StatusCode.Unknown)
                }
                callback.onResponse(this@RequestCall, Response.success(result))
            }
        })
    }

    override fun cloneImpl(): Call<Result<T>> = RequestCall(proxy.clone())

    override fun timeout(): Timeout = proxy.timeout()
}