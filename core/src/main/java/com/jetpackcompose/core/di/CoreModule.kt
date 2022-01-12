package com.jetpackcompose.core.di

import com.google.gson.GsonBuilder
import com.jetpackcompose.core.BuildConfig
import com.jetpackcompose.core.util.calladapter.adapter.CallAdapterFactory
import com.jetpackcompose.core.util.interceptors.AuthorizationInterceptor
import com.jetpackcompose.core.util.token.TokenManager
import com.jetpackcompose.core.util.token.TokenManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
object CoreModule {

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

    //the @Named help us to inject a specific string into a class if we have multiple string
    //we can get token from server and store it with sharedPreferences and get it in TokenManager class
    //and remove this provider
    @Singleton
    @Provides
    @Named("token")
    fun provideToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

    @Singleton
    @Provides
    fun provideRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CallAdapterFactory())
            .client(okHttpClient(interceptor))
            .build()
    }

    private fun okHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)

            if (BuildConfig.DEBUG)
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )

        }.build()
    }

}