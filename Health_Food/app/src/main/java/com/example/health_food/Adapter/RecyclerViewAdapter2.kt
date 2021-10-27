package com.example.health_food.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.health_food.R

class RecyclerViewAdapter2(val itemlist: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {
    class ViewHolder {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_refrigerator,parent,false)
        return RecyclerViewAdapter2.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position])
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}