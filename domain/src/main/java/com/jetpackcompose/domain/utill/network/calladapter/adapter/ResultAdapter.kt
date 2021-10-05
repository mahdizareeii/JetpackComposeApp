package com.jetpackcompose.domain.utill.network.calladapter.adapter

import com.jetpackcompose.domain.utill.network.calladapter.Result
import com.jetpackcompose.domain.utill.network.calladapter.call.RequestCall
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<Result<Type>>> {
    override fun responseType(): Type = type
    override fun adapt(call: Call<Type>): Call<Result<Type>> = RequestCall(call)
}