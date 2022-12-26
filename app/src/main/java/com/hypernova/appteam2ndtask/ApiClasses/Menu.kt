package com.hypernova.appteam2ndtask.ApiClasses

import com.google.gson.annotations.SerializedName

data class Menu(@SerializedName("menuItems") var menuItems: List<MenuItems>)

data class MenuItems(

    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("image") var image: String
)

data class MenuInfo(

    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("restaurantChain") var restaurantChain: String,
    @SerializedName("breadcrumbs") var breadcrumbs: List<String>,
    @SerializedName("numberOfServings") var numberOfServings: Int,
    @SerializedName("price") var price: Float,
    @SerializedName("spoonacularScore") var spoonacularScore: Float
)
