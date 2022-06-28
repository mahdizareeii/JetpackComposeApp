package com.jetpackcompose.app.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.jetpackcompose.core.util.base.BaseNavGraph
import com.jetpackcompose.core.util.qualifiers.Qualifiers
import com.jetpackcompose.resources.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Qualifiers.NavGraphs.AppNavGraph
    @Inject
    lateinit var appNavGraph: BaseNavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                appNavGraph.createNavGraph(rememberNavController())
            }
        }
    }
}