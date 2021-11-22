package com.example.health_food.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.health_food.R
import com.example.health_food.model.Refrigerator

class RecyclerViewAdapter2(val itemlist: ArrayList<String>, val itemClick : (String) -> Unit): RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {
    inner class ViewHolder(v: View, itemClick: (String) -> Unit): RecyclerView.ViewHolder(v) {
        fun bind(item: String){
            itemView.findViewById<TextView>(R.id.refrigerator_item_txt).text = item
            itemView.findViewById<Button>(R.id.delete_refrigerator_btn).setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_refrigerator_text,parent,false)
        return ViewHolder(inflatedView, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position])
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}