package com.example.health_food.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.health_food.R
import com.example.health_food.model.Refrigerator

class RecyclerViewAdapter(val itemlist: ArrayList<Refrigerator>, val context: Context?, val selectedlist: ArrayList<Refrigerator>,val itemClick: (Refrigerator) -> Unit): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(item: Refrigerator, context: Context?, selectedlist: ArrayList<Refrigerator>,itemClick: (Refrigerator) -> Unit){
            var choice = 1
            for(i in selectedlist){
                if(i.id == item.id){
                    choice = 0
                }
            }
            if(choice == 1){
                Glide.with(context!!)
                        .load("http://192.168.137.1:3000/images/"+ item.url)
                        .into(itemView.findViewById(R.id.refrigerator_img))
            }
            itemView.findViewById<TextView>(R.id.refrigerator_txt).text = item.name
            itemView.findViewById<LinearLayout>(R.id.refrigerator_layout).setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_refrigerator,parent,false)
        return RecyclerViewAdapter.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position], context, selectedlist,itemClick)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}