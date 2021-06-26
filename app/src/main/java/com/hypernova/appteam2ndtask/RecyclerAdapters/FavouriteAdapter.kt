package com.hypernova.appteam2ndtask.RecyclerAdapters

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
import com.hypernova.appteam2ndtask.InfoActivity
import com.hypernova.appteam2ndtask.LocalStorage.DataModel
import com.hypernova.appteam2ndtask.R

class FavouriteAdapter(private val taskModelList: List<DataModel>) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>()  {
    lateinit var context: Context
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textView)
        val image: ImageView = view.findViewById(R.id.image)
        val card: RelativeLayout = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskModelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = taskModelList[position].name
        Glide.with(context).load(taskModelList[position].image).into(holder.image)
        holder.card.setOnClickListener {
            context.startActivity(Intent(context, InfoActivity::class.java).apply {
                putExtra("id", taskModelList[position].id)
                putExtra("name", taskModelList[position].name)
                putExtra("image", taskModelList[position].image)
            })
        }
    }
}
