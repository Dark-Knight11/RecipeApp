package com.hypernova.appteam2ndtask.LocalStorage

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object PrefConfig {
    private val LIST_KEY = "favourites"
    fun writeListInPref(context: Context?, list: List<DataModel?>?) {
        val gson = Gson()
        val jsonString = gson.toJson(list)
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.putString(LIST_KEY, jsonString)
        editor.apply()
    }

    fun readListFromPref(context: Context?): List<DataModel> {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString = pref.getString(LIST_KEY, "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<DataModel>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    fun clearListFromPref(context: Context?) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}
