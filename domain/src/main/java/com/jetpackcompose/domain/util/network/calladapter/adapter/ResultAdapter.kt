package com.jetpackcompose.domain.util.network.calladapter.adapter

import com.jetpackcompose.domain.util.DataState
import com.jetpackcompose.domain.util.network.calladapter.call.RequestCall
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<DataState<Type>>> {
    override fun responseType(): Type = type
    override fun adapt(call: Call<Type>): Call<DataState<Type>> = RequestCall(call)
}