package com.jetpackcompose.app.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.gson.GsonBuilder
import com.jetpackcompose.app.network.api.RecipeApiService
import com.jetpackcompose.app.ui.theme.ComposeAppTheme
import com.jetpackcompose.app.ui.theme.element.RecipeFood
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var stuffString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                RecipeFood()
            }
        }
        Log.i("MainActivity", "string injected: $stuffString")
        requestToServer()
    }

    private fun requestToServer() {
        val service = Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeApiService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getRecipeById(
                "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                2175
            )

            Log.i("MainActivity", "requestToServer: " + response.title)
        }
    }
}