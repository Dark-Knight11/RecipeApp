package com.hypernova.appteam2ndtask.RecyclerAdapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hypernova.appteam2ndtask.ApiClasses.IngredientsInfo
import com.hypernova.appteam2ndtask.R

class IngredientsAdapter(val res: IngredientsInfo?) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    lateinit var context: Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val amount: TextView = view.findViewById(R.id.amount)
        val imageView: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ing_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return res?.ingredients?.size!!
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val base = res?.ingredients?.get(position)
        holder.name.text = base?.name
        holder.amount.text = base?.amount?.metric?.value.toString() + " " + base?.amount?.metric?.unit
        Glide.with(context).load("https://spoonacular.com/cdn/ingredients_100x100/" + base?.image).into(holder.imageView)
    }
}
