package com.jetpackcompose.domain.di

import com.google.gson.GsonBuilder
import com.jetpackcompose.domain.network.api.RecipeApiService
import com.jetpackcompose.domain.utill.network.AuthorizationInterceptor
import com.jetpackcompose.domain.utill.network.TokenManager
import com.jetpackcompose.domain.utill.network.TokenManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 *  you should give the scope to @InstallIn() to inject something based on this document
 *  https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideService(interceptor: Interceptor): RecipeApiService = Retrofit.Builder()
        .baseUrl("https://food2fork.ca/api/recipe/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient(interceptor))
        .build()
        .create(RecipeApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthorizationInterceptor(tokenManager: TokenManager): Interceptor {
        return AuthorizationInterceptor(tokenManager)
    }

    @Singleton
    @Provides
    fun provideTokenManager(@Named("token") token: String): TokenManager {
        return TokenManagerImpl(token)
    }

    private fun okHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

}