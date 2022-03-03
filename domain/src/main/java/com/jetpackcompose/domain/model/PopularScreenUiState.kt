package com.jetpackcompose.domain.model

sealed interface PopularScreenUIState {
    data class MostSells(val list: List<Recipe>) : PopularScreenUIState
    data class CheapProducts(val list: List<Recipe>) : PopularScreenUIState
    data class DessertProducts(val list: List<Recipe>) : PopularScreenUIState
}