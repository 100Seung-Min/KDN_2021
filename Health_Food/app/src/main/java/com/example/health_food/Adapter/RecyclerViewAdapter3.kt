package com.example.health_food.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_food.R
import com.example.health_food.model.CommunityDTO

class RecyclerViewAdapter3(val itemlist: ArrayList<CommunityDTO>): RecyclerView.Adapter<RecyclerViewAdapter3.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(item: CommunityDTO){
            itemView.findViewById<TextView>(R.id.user_name_txt).text = item.user_ID
            itemView.findViewById<TextView>(R.id.detail_txt).text = item.content
            itemView.findViewById<TextView>(R.id.hashtag_txt).text = "#" + item.tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_community,parent,false)
        return RecyclerViewAdapter3.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position])
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}