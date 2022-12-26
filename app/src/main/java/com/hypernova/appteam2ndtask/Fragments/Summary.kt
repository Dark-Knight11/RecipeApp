package com.hypernova.appteam2ndtask.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hypernova.appteam2ndtask.ApiClasses.APIService
import com.hypernova.appteam2ndtask.ApiClasses.MenuInfo
import com.hypernova.appteam2ndtask.BuildConfig
import com.hypernova.appteam2ndtask.MenuInfoActivity
import com.hypernova.appteam2ndtask.R
import kotlin.properties.Delegates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Summary : Fragment() {

    var getId by Delegates.notNull<Int>()
    val key = BuildConfig.API_KEY

    lateinit var restaurant: TextView
    lateinit var price: TextView
    lateinit var serving: TextView
    lateinit var type: TextView
    lateinit var rating: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity: MenuInfoActivity = activity as MenuInfoActivity
        getId = activity.sendId()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_summary, container, false)

        restaurant = view.findViewById(R.id.restaurant)
        price = view.findViewById(R.id.price)
        serving = view.findViewById(R.id.serving)
        type = view.findViewById(R.id.type)
        rating = view.findViewById(R.id.rating)

        getAPi()
        return view
    }

    private fun getAPi() {
        val call = APIService.api_instance.getMenuInfo(getId, key)
        call.enqueue(object : Callback<MenuInfo> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<MenuInfo>, response: Response<MenuInfo>) {
                if (response.code() == 402)
                    Toast.makeText(context, "Quota Finished", Toast.LENGTH_SHORT).show()
                else {
                    val res: MenuInfo? = response.body()
                    restaurant.text = "Restaurant: " + res?.restaurantChain
                    price.text = "Price: " + res?.price.toString()
                    serving.text = "Number of Servings: " + res?.numberOfServings.toString()
                    type.text = "Type: " + res?.breadcrumbs?.get(0)
                    rating.text = "Rating: " + res?.spoonacularScore.toString()
                }
            }

            override fun onFailure(call: Call<MenuInfo>, t: Throwable) {
                Log.i("getMenuInfo ", "Error: ", t)
            }
        })
    }
}
