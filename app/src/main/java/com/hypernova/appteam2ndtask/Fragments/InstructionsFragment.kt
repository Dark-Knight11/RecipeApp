package com.hypernova.appteam2ndtask.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hypernova.appteam2ndtask.ApiClasses.APIService
import com.hypernova.appteam2ndtask.ApiClasses.Instructions
import com.hypernova.appteam2ndtask.BuildConfig
import com.hypernova.appteam2ndtask.InfoActivity
import com.hypernova.appteam2ndtask.R
import com.hypernova.appteam2ndtask.RecyclerAdapters.InstructionsAdapter
import kotlin.properties.Delegates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstructionsFragment : Fragment() {

    val key: String = BuildConfig.API_KEY
    lateinit var recyclerView: RecyclerView
    var getData by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity: InfoActivity? = activity as InfoActivity?
        getData = activity?.sendData()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_instructions, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        getApi()

        return view
    }

    private fun getApi() {
        val call = APIService.api_instance.getInstructions(getData, key)
        call.enqueue(object : Callback<List<Instructions>> {
            override fun onResponse(
                call: Call<List<Instructions>>,
                response: Response<List<Instructions>>
            ) {
                if (response.code() == 402)
                    Toast.makeText(context, "Quota Finished", Toast.LENGTH_SHORT).show()
                else {
                    if (response.body()?.size != 0) {
                        val res: List<Instructions>? = response.body()
                        recyclerView.adapter = InstructionsAdapter(res)
                    } else
                        Toast.makeText(context, "No Intructions", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<Instructions>>, t: Throwable) {
                Log.e("RecipeInfo Fetch", "Error: ", t)
            }
        })
    }
}
