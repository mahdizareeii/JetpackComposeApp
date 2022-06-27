package com.jetpackcompose.core.util.qualifiers

import javax.inject.Qualifier

object Qualifiers {
    object NavGraphs {
        @Qualifier
        @Retention(AnnotationRetention.BINARY)
        annotation class AppNavGraph

        @Qualifier
        @Retention(AnnotationRetention.BINARY)
        annotation class HomeNavGraph
    }
}