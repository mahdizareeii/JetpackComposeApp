package com.jetpackcompose.domain.network.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("pk")
    val primaryKey: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("cooking_instructions")
    val cookingInstructions: String? = null,
    @SerializedName("date_added")
    val dateAdded: String? = null,
    @SerializedName("date_updated")
    val dateUpdated: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("featured_image")
    val featuredImage: String? = null,
    @SerializedName("ingredients")
    val ingredients: List<String>? = null,
    @SerializedName("long_date_added")
    val longDateAdded: Int? = null,
    @SerializedName("long_date_updated")
    val longDateUpdated: Int? = null,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("rating")
    val rating: Int? = null,
    @SerializedName("source_url")
    val sourceUrl: String? = null
)
