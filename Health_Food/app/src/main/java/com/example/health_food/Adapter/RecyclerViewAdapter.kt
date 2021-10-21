package com.example.health_food.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.health_food.R
import com.example.health_food.model.Refrigerator

class RecyclerViewAdapter(val itemlist: ArrayList<Refrigerator>, val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(item: Refrigerator, context: Context){
            Glide.with(context)
                    .load(item.foodurl)
                    .into(itemView.findViewById(R.id.refrigerator_img))
            itemView.findViewById<TextView>(R.id.refrigerator_txt).text = item.foodname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_refrigerator,parent,false)
        return RecyclerViewAdapter.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position], context)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}