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

class RecyclerViewAdapter2(val itemlist: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(item: String, position: Int){
            if(position != 0){
                itemView.findViewById<LinearLayout>(R.id.slide_recipe).visibility = View.GONE
                itemView.findViewById<LinearLayout>(R.id.slide_menu).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.refrigerator_item_txt).text = item
            }
            else{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_refrigerator_text,parent,false)
        return RecyclerViewAdapter2.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position], position)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}