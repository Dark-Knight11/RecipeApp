package com.hypernova.appteam2ndtask.RecyclerAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hypernova.appteam2ndtask.ApiClasses.SearchResult
import com.hypernova.appteam2ndtask.InfoActivity
import com.hypernova.appteam2ndtask.R

class RecyclerAdapter(val res: SearchResult?) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    lateinit var context: Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.name)
        val recipe: RelativeLayout = view.findViewById(R.id.recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = res?.results?.get(position)?.title
        holder.recipe.setOnClickListener {
            context.startActivity(
                Intent(context, InfoActivity::class.java).apply {
                    putExtra("id", res?.results?.get(position)?.id)
                    putExtra("name", res?.results?.get(position)?.title)
                    putExtra("image", res?.results?.get(position)?.image)
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return res?.results?.size!!
    }
}
