package com.hypernova.appteam2ndtask.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.hypernova.appteam2ndtask.BuildConfig
import com.hypernova.appteam2ndtask.InfoActivity
import com.hypernova.appteam2ndtask.R
import kotlin.properties.Delegates

class Nutrition : Fragment() {

    var getId by Delegates.notNull<Int>()
    val apiKey = BuildConfig.API_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity: InfoActivity? = activity as InfoActivity?
        getId = activity?.sendData()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_nutrition, container, false)

        val myWebView: WebView = view.findViewById(R.id.webView)
        myWebView.loadUrl("https://api.spoonacular.com/recipes/$getId/nutritionWidget?defaultCss=true&apiKey=$apiKey")

        return view
    }
}
