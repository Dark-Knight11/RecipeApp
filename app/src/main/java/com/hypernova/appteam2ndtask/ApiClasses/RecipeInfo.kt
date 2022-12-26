package com.hypernova.appteam2ndtask.ApiClasses

import com.google.gson.annotations.SerializedName

data class Random(@SerializedName("recipes") var recipes: List<RecipeInfo>)

data class RecipeInfo(

    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("image") var image: String,
    @SerializedName("readyInMinutes") var readyInMinutes: Int,
    @SerializedName("vegetarian") var vegetarian: Boolean,
    @SerializedName("summary") var summary: String,
    @SerializedName("pricePerServing") var pricePerServing: Float,
    @SerializedName("instructions") var instructions: String
)
