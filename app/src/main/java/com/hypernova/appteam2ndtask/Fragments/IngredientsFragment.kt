package com.hypernova.appteam2ndtask.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hypernova.appteam2ndtask.ApiClasses.APIService
import com.hypernova.appteam2ndtask.ApiClasses.IngredientsInfo
import com.hypernova.appteam2ndtask.BuildConfig
import com.hypernova.appteam2ndtask.InfoActivity
import com.hypernova.appteam2ndtask.R
import com.hypernova.appteam2ndtask.RecyclerAdapters.IngredientsAdapter
import kotlin.properties.Delegates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientsFragment : Fragment() {

    private var getId by Delegates.notNull<Int>()
    private val key = BuildConfig.API_KEY
    lateinit var ingredientsRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity: InfoActivity = activity as InfoActivity
        getId = activity.sendData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ingredients, container, false)

        ingredientsRecycler = view.findViewById(R.id.ingredientsRecylcer)
        ingredientsRecycler.layoutManager = GridLayoutManager(context, 2)

        getApi()
        return view
    }

    private fun getApi() {
        val call = APIService.api_instance.getIngredients(getId, key)
        call.enqueue(object : Callback<IngredientsInfo> {
            override fun onResponse(
                call: Call<IngredientsInfo>,
                response: Response<IngredientsInfo>
            ) {
                if (response.code() == 402)
                    Toast.makeText(context, "Quota Finished", Toast.LENGTH_SHORT).show()
                else {
                    val res: IngredientsInfo? = response.body()
                    ingredientsRecycler.adapter = IngredientsAdapter(res)
                }
            }
            override fun onFailure(call: Call<IngredientsInfo>, t: Throwable) {
                Log.i("getIngredients", "error: ", t)
            }
        })
    }
}
