package com.jetpackcompose.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 *  you should give the scope to @InstallIn() to inject something based on this document
 *  https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //the @Named help us to inject a specific string into a class if we have multiple string
    //we can get token from server and store it with sharedPreferences and get it in TokenManager class
    //and remove this provider
    @Singleton
    @Provides
    @Named("token")
    fun provideToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}