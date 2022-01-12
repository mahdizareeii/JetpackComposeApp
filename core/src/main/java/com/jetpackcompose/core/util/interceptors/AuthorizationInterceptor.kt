package com.jetpackcompose.core.util.interceptors

import com.jetpackcompose.core.util.token.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().apply {
            header("Authorization", tokenManager.getToken())
        }.build()
        return chain.proceed(newRequest)
    }
}