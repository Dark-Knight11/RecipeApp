package com.hypernova.appteam2ndtask.RecyclerAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hypernova.appteam2ndtask.ApiClasses.Menu
import com.hypernova.appteam2ndtask.MenuInfoActivity
import com.hypernova.appteam2ndtask.R

class MenuAdapter(val res: Menu?) : RecyclerView.Adapter<MenuAdapter.ViewHolder>()  {
    lateinit var context: Context
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val image: de.hdodenhof.circleimageview.CircleImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val menuItem: RelativeLayout = view.findViewById(R.id.menuItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(res?.menuItems?.get(position)?.image).into(holder.image)
        holder.title.text = res?.menuItems?.get(position)?.title
        holder.menuItem.setOnClickListener {
            context.startActivity(Intent(context, MenuInfoActivity::class.java).apply {
                putExtra("id", res?.menuItems?.get(position)?.id)
                putExtra("name", res?.menuItems?.get(position)?.title)
                putExtra("image", res?.menuItems?.get(position)?.image)
            })
        }
    }

    override fun getItemCount(): Int {
        return res?.menuItems?.size!!
    }
}
