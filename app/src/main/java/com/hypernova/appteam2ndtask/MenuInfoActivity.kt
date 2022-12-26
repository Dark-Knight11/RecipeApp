package com.hypernova.appteam2ndtask

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.hypernova.appteam2ndtask.Fragments.FragmentPagerAdapter
import com.hypernova.appteam2ndtask.Fragments.MenuNutrition
import com.hypernova.appteam2ndtask.Fragments.Summary
import kotlin.properties.Delegates

class MenuInfoActivity : AppCompatActivity() {

    lateinit var poster: de.hdodenhof.circleimageview.CircleImageView
    lateinit var title: TextView
    lateinit var tabLayout: TabLayout
    lateinit var toolbar: Toolbar
    lateinit var viewPager: ViewPager

    var id by Delegates.notNull<Int>()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_info)

        poster = findViewById(R.id.poster)
        title = findViewById(R.id.title)
        tabLayout = findViewById(R.id.tabLayout)
        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.pager)

        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        id = intent.getIntExtra("id", 0)

        Glide.with(this).load(image).into(poster)
        title.text = name

        toolbar.title = ""
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_ATOP)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(Summary())
        fragments.add(MenuNutrition())

        val pagerAdapter = FragmentPagerAdapter(supportFragmentManager, this, fragments)
        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.text = "Summary"
        tabLayout.getTabAt(1)!!.text = "Nutrition"
    }

    fun sendId(): Int {
        return id
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
