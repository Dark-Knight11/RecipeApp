package com.hypernova.appteam2ndtask.Fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class FragmentPagerAdapter(
    fm: FragmentManager?,
    var context: Context,
    var fragments: ArrayList<Fragment>
) :
    FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}
