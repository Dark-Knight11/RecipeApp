package com.hypernova.appteam2ndtask.RecyclerAdapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hypernova.appteam2ndtask.ApiClasses.Instructions
import com.hypernova.appteam2ndtask.R

class InstructionsAdapter(val res: List<Instructions>?) : RecyclerView.Adapter<InstructionsAdapter.ViewHolder>() {
    lateinit var context: Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var no: TextView = view.findViewById(R.id.no)
        var step: TextView = view.findViewById(R.id.step)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.instructions_card, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val base = res?.get(0)?.steps
        holder.no.text = base?.get(position)?.number.toString() + "."
        holder.step.text = base?.get(position)?.step
    }

    override fun getItemCount(): Int {
        return res?.get(0)?.steps?.size!!
    }
}
