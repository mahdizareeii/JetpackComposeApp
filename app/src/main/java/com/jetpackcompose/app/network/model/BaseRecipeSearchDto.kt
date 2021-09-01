package com.jetpackcompose.app.network.model

import com.google.gson.annotations.SerializedName


data class BaseRecipeSearchDto<T>(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("results")
    val results: T? = null
)
