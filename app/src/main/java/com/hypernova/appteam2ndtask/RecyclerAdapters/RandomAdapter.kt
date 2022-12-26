package com.hypernova.appteam2ndtask.RecyclerAdapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hypernova.appteam2ndtask.ApiClasses.Random
import com.hypernova.appteam2ndtask.InfoActivity
import com.hypernova.appteam2ndtask.R

class RandomAdapter(val rad: Random?) : RecyclerView.Adapter<RandomAdapter.ViewHolder>() {

    lateinit var context: Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishName: TextView = view.findViewById(R.id.dishName)
        val time: TextView = view.findViewById(R.id.time)
        val price: TextView = view.findViewById(R.id.price)
        val imageView: ImageView = view.findViewById(R.id.poster)
        val recipe: RelativeLayout = view.findViewById(R.id.recipe)
        val veg: ImageView = view.findViewById(R.id.veg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dishName.text = rad?.recipes?.get(position)?.title
        Glide.with(context).load(rad?.recipes?.get(position)?.image).into(holder.imageView)
        holder.time.text = "Time to make: " + rad?.recipes?.get(position)?.readyInMinutes.toString() + " Min"
        holder.price.text = "Price: " + rad?.recipes?.get(position)?.pricePerServing.toString()
        if (rad?.recipes?.get(position)?.vegetarian == true) holder.veg.setImageResource(R.drawable.ic_veg)
        else holder.veg.setImageResource(R.drawable.ic_nonveg)
        holder.recipe.setOnClickListener {
            context.startActivity(
                Intent(context, InfoActivity::class.java).apply {
                    putExtra("id", rad?.recipes?.get(position)?.id)
                    putExtra("name", rad?.recipes?.get(position)?.title)
                    putExtra("image", rad?.recipes?.get(position)?.image)
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return rad?.recipes?.size!!
    }
}
