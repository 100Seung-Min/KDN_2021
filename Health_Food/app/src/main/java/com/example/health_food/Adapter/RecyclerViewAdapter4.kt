package com.example.health_food.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.health_food.R
import com.example.health_food.model.FrigeRecipe
import com.example.health_food.model.Refrigerator

class RecyclerViewAdapter4(val itemlist: ArrayList<FrigeRecipe>, val context: Context, val itemClick: (FrigeRecipe) -> Unit): RecyclerView.Adapter<RecyclerViewAdapter4.ViewHolder>() {
    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(item: FrigeRecipe, context: Context, itemClick: (FrigeRecipe) -> Unit){
            Glide.with(context)
                .load("http://192.168.137.1:3000/images/" + item.url)
                .into(itemView.findViewById(R.id.frigeRecipe_img))
            itemView.findViewById<TextView>(R.id.frigeRecipe_txt).text = item.name
            itemView.findViewById<LinearLayout>(R.id.frigeRecipe_layout).setOnClickListener {
                itemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_frigerecipe,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position], context, itemClick)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}