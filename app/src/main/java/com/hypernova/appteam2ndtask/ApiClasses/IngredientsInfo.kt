package com.hypernova.appteam2ndtask.ApiClasses

import com.google.gson.annotations.SerializedName

data class IngredientsInfo(
    @SerializedName("ingredients") var ingredients : List<Ingredients>
)

data class Ingredients (
    @SerializedName("amount") var amount : Amount,
    @SerializedName("image") var image : String,
    @SerializedName("name") var name : String
)

data class Amount (
    @SerializedName("metric") var metric : Metric,
)

data class Metric (

    @SerializedName("unit") var unit : String,
    @SerializedName("value") var value : Float

)
