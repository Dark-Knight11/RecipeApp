package com.hypernova.appteam2ndtask.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hypernova.appteam2ndtask.APIService
import com.hypernova.appteam2ndtask.ApiClasses.Menu
import com.hypernova.appteam2ndtask.BuildConfig
import com.hypernova.appteam2ndtask.R
import com.hypernova.appteam2ndtask.RecyclerAdapters.MenuAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuFragment : Fragment() {

    val key = BuildConfig.API_KEY
    lateinit var burgerLayout: RecyclerView
    lateinit var pizzaLayout: RecyclerView
    lateinit var cakeLayout: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_menu, container, false)

        burgerLayout = view.findViewById(R.id.burgerLayout)
        pizzaLayout = view.findViewById(R.id.pizzalayout)
        cakeLayout = view.findViewById(R.id.cakeLayout)
        progressBar = view.findViewById(R.id.progress_circular)

        burgerLayout.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        pizzaLayout.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cakeLayout.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        getMenu("burger", burgerLayout)
        getMenu("pizza", pizzaLayout)
        getMenu("cake", cakeLayout)

        return view
    }

    fun getMenu(query: String, layout: RecyclerView) {
        val call = APIService.api_instance.getMenu(key, query)
        call.enqueue(object: Callback<Menu> {
            override fun onResponse(call: Call<Menu>, response: Response<Menu>) {
                if(response.code() == 402)
                    Toast.makeText(context, "Quota Finished", Toast.LENGTH_SHORT).show()
                else {
                    val res: Menu? = response.body()
                    layout.adapter = MenuAdapter(res)
                }
                progressBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<Menu>, t: Throwable) {
                Log.i("get Menu", "Error: ", t)
            }
        })
    }

}