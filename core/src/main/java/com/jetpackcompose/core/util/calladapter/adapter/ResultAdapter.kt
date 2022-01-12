package com.jetpackcompose.core.util.calladapter.adapter

import com.jetpackcompose.core.model.NetworkDataState
import com.jetpackcompose.core.util.calladapter.call.RequestCall
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<NetworkDataState<Type>>> {
    override fun responseType(): Type = type
    override fun adapt(call: Call<Type>): Call<NetworkDataState<Type>> = RequestCall(call)
}