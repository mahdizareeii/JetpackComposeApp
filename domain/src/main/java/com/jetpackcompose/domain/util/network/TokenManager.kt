package com.jetpackcompose.domain.util.network

interface TokenManager {
    fun getToken(): String
    fun setToken()
    fun clearToken()
}

class TokenManagerImpl(
    private val token: String
) : TokenManager {
    override fun getToken(): String = token

    override fun setToken() {
        TODO()
    }

    override fun clearToken() {
        TODO()
    }
}