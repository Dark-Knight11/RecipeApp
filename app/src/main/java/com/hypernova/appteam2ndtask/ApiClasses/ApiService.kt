package com.hypernova.appteam2ndtask

import com.hypernova.appteam2ndtask.ApiClasses.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("recipes/complexSearch")
    fun getresults(
        @Query("apiKey") key: String,
        @Query("query") term: String
    ): Call<SearchResult>

    @GET("recipes/random")
    fun getRandom(
        @Query("apiKey") key: String,
        @Query("number") no: Int
    ): Call<Random>

    @GET("recipes/{id}/analyzedInstructions")
    fun getInstructions(
        @Path("id") id: Int,
        @Query("apiKey") key: String,
    ): Call<List<Instructions>>

    @GET("recipes/{id}/ingredientWidget.json")
    fun getIngredients(
        @Path("id") id: Int,
        @Query("apiKey") key: String
    ): Call<IngredientsInfo>

    @GET("food/menuItems/search")
    fun getMenu(
        @Query("apiKey") key: String,
        @Query("query") query: String
    ): Call<Menu>

    @GET("food/menuItems/{id}")
    fun getMenuInfo(
        @Path("id") id: Int,
        @Query("apiKey") key: String
    ): Call<MenuInfo>
}

object APIService {
    val api_instance: ApiInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api_instance = retrofit.create(ApiInterface::class.java)
    }
}
