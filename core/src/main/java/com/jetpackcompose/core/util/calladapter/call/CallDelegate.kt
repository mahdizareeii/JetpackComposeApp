package com.jetpackcompose.core.util.calladapter.call

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CallDelegate<In, Out>(
    protected val proxy: Call<In>
) : Call<Out> {

    override fun execute(): Response<Out> = throw NotImplementedError()
    final override fun enqueue(callback: Callback<Out>) = enqueueImpl(callback)
    final override fun clone(): Call<Out> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<Out>)
    abstract fun cloneImpl(): Call<Out>

}