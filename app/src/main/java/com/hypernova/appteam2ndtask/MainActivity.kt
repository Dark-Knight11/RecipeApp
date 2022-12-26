package com.hypernova.appteam2ndtask

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hypernova.appteam2ndtask.Fragments.FavouriteFragment
import com.hypernova.appteam2ndtask.Fragments.HomeFragment
import com.hypernova.appteam2ndtask.Fragments.MenuFragment
import com.hypernova.appteam2ndtask.LocalStorage.DataModel
import com.hypernova.appteam2ndtask.LocalStorage.PrefConfig

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var imageView: ImageView
    lateinit var ft: FragmentManager
    lateinit var bottomNav: BottomNavigationView

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)

        val pref: SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val firstStart: Boolean = pref.getBoolean("firstStart", true)
        if (firstStart) setData()

        ft = supportFragmentManager
        bottomNav.setOnNavigationItemSelectedListener {
            val fragment: Fragment = when (it.itemId) {
                R.id.home -> HomeFragment()
                R.id.menu -> MenuFragment()
                R.id.fav -> FavouriteFragment()
                else -> HomeFragment()
            }
            ft.beginTransaction().replace(R.id.fl_wrapper, fragment).commit()
            return@setOnNavigationItemSelectedListener true
        }
        bottomNav.selectedItemId = R.id.home
    }

    private fun setData() {
        var taskList: List<DataModel> = emptyList()
        val dataModel = DataModel("name", 1234, "https://images.unsplash.com/photo-1578985545062-69928b1d9587?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80")
        taskList = taskList + dataModel
        PrefConfig.writeListInPref(this, taskList)
        val pref: SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }
}
