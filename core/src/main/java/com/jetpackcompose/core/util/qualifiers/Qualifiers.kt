package com.jetpackcompose.core.util.qualifiers

import javax.inject.Qualifier

object Qualifiers {
    object NavGraphs {
        @Qualifier
        annotation class AppNavGraph

        @Qualifier
        annotation class HomeNavGraph
    }
}