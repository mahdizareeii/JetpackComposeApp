package com.jetpackcompose.domain.util.network.calladapter.adapter

import com.jetpackcompose.domain.util.DataState
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        return when (getRawType(returnType)) {
            Call::class.java -> {
                val callType = getParameterUpperBound(0, returnType as ParameterizedType)
                when (getRawType(callType)) {
                    DataState::class.java -> ResultAdapter(
                        getParameterUpperBound(0, callType as ParameterizedType)
                    )
                    else -> null
                }
            }
            else -> null
        }
    }
}