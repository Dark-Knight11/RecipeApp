package com.hypernova.appteam2ndtask

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.hypernova.appteam2ndtask.Fragments.FragmentPagerAdapter
import com.hypernova.appteam2ndtask.Fragments.IngredientsFragment
import com.hypernova.appteam2ndtask.Fragments.InstructionsFragment
import com.hypernova.appteam2ndtask.Fragments.Nutrition
import com.hypernova.appteam2ndtask.LocalStorage.DataModel
import com.hypernova.appteam2ndtask.LocalStorage.PrefConfig
import kotlin.properties.Delegates

class InfoActivity : AppCompatActivity() {
    lateinit var title: TextView
    private lateinit var header: ImageView
    lateinit var tabLayout: TabLayout
    lateinit var toolbar: Toolbar
    lateinit var viewPager: ViewPager
    lateinit var fb: FloatingActionButton
    var id by Delegates.notNull<Int>()
    var flag: Boolean = true

    lateinit var taskList: List<DataModel>

    @SuppressLint("NewApi", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        header = findViewById(R.id.header)
        title = findViewById(R.id.title)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.pager)
        toolbar = findViewById(R.id.toolbar)
        fb = findViewById(R.id.fb)

        toolbar.title = ""
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")

        title.text = name
        Glide.with(this).load(image).into(header)

        taskList = PrefConfig.readListFromPref(this)
        fb.setOnClickListener {
            val dataModel = DataModel(name, id, image)
            for (element in taskList)
                if (element.id == id)
                    flag = false

            if (flag) {
                taskList = taskList + dataModel
                PrefConfig.writeListInPref(this, taskList)
                Toast.makeText(this, "Added to Favourites", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Already added to Favourites", Toast.LENGTH_SHORT).show()
        }

        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())
        fragments.add(Nutrition())

        val pagerAdapter = FragmentPagerAdapter(supportFragmentManager, this, fragments)
        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.text = "Ingredients"
        tabLayout.getTabAt(1)!!.text = "Instructions"
        tabLayout.getTabAt(2)!!.text = "Nutrition"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun sendData(): Int {
        return id
    }
}
