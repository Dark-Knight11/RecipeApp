package com.hypernova.appteam2ndtask.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hypernova.appteam2ndtask.LocalStorage.DataModel
import com.hypernova.appteam2ndtask.LocalStorage.PrefConfig
import com.hypernova.appteam2ndtask.R
import com.hypernova.appteam2ndtask.RecyclerAdapters.FavouriteAdapter

class FavouriteFragment : Fragment() {

    lateinit var taskList: List<DataModel>
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        taskList = PrefConfig.readListFromPref(context)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = FavouriteAdapter(taskList)
        return view
    }
}
