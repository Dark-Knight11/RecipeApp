package com.hypernova.appteam2ndtask.Fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.hypernova.appteam2ndtask.ApiClasses.APIService
import com.hypernova.appteam2ndtask.ApiClasses.Random
import com.hypernova.appteam2ndtask.ApiClasses.SearchResult
import com.hypernova.appteam2ndtask.BuildConfig
import com.hypernova.appteam2ndtask.R
import com.hypernova.appteam2ndtask.RecyclerAdapters.RandomAdapter
import com.hypernova.appteam2ndtask.RecyclerAdapters.RecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    val key: String = BuildConfig.API_KEY
    lateinit var recyclerView: RecyclerView
    lateinit var appBar: AppBarLayout
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val search: SearchView = view.findViewById(R.id.search)
        appBar = view.findViewById(R.id.app_bar)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progress_circular)

        recyclerView.layoutManager = LinearLayoutManager(context)

        getRandom()

        search.setOnSearchClickListener {
            appBar.setExpanded(false)
        }

        search.setOnCloseListener {
            appBar.setExpanded(true)
            search.onActionViewCollapsed()
            getRandom()
            true
        }

        search.layoutParams = Toolbar.LayoutParams(Gravity.END)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getApi(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                getApi(newText)
                return true
            }
        })
        return view
    }

    fun getApi(query: String) {
        val call = APIService.api_instance.getresults(key, query)
        call.enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                if (response.code() == 402)
                    Toast.makeText(context, "Quota Finished", Toast.LENGTH_SHORT).show()
                else {
                    val resultSearchResult: SearchResult? = response.body()
                    Log.i("onResponse", response.code().toString())
                    recyclerView.adapter = RecyclerAdapter(resultSearchResult)
                }
            }
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Log.d("API Fetch", "Error while fetching", t)
            }
        })
    }

    private fun getRandom() {
        val call = APIService.api_instance.getRandom(key, 20)
        call.enqueue(object : Callback<Random> {
            override fun onResponse(call: Call<Random>, response: Response<Random>) {
                if (response.code() == 402)
                    Toast.makeText(context, "Quota Finished", Toast.LENGTH_SHORT).show()
                else {
                    val res: Random? = response.body()
                    recyclerView.adapter = RandomAdapter(res)
                }
                progressBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<Random>, t: Throwable) {
                Log.d("Random Recipe Fetch", "Error while fetching", t)
            }
        })
    }
}
