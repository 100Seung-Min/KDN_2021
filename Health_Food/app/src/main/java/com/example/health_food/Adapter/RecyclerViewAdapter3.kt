package com.example.health_food.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.health_food.AddCommunityImage
import com.example.health_food.ImageChange.ImageChange
import com.example.health_food.R
import com.example.health_food.model.CommunityDTO
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class RecyclerViewAdapter3(val itemlist: ArrayList<CommunityDTO>, val context: Context?): RecyclerView.Adapter<RecyclerViewAdapter3.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        var stringToImage: Bitmap? = null

        fun bind(item: CommunityDTO, context: Context?){
            itemView.findViewById<TextView>(R.id.user_name_txt).text = item.user_ID
            itemView.findViewById<TextView>(R.id.detail_txt).text = item.content
            itemView.findViewById<TextView>(R.id.hashtag_txt).text = "#" + item.tag
            itemView.findViewById<ImageView>(R.id.detail_setting_img).setOnClickListener {
                itemView.findViewById<ImageView>(R.id.detail_setting_img).visibility = View.GONE
                itemView.findViewById<LinearLayout>(R.id.setting_layout).visibility = View.VISIBLE
            }
            Glide.with(context!!)
                .load("http://10.120.74.70:3000/images/" + item.picture)
                .into(itemView.findViewById<ImageView>(R.id.detail_img))
            itemView.findViewById<LinearLayout>(R.id.content_layout).setOnClickListener {
                itemView.findViewById<ImageView>(R.id.detail_setting_img).visibility = View.VISIBLE
                itemView.findViewById<LinearLayout>(R.id.setting_layout).visibility = View.GONE
            }
            itemView.findViewById<TextView>(R.id.setting_update).setOnClickListener {

            }
            itemView.findViewById<TextView>(R.id.setting_delete).setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_community,parent,false)
        return RecyclerViewAdapter3.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position], context)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}