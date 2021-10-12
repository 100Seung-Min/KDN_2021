package com.example.health_food.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.health_food.R
import com.example.health_food.model.Food

class ListViewAdapter(val food: ArrayList<Food>): BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_food, null)
        val foodtext = view.findViewById<TextView>(R.id.item_food_txt)
        foodtext.text = food[position].fooditem
        return view
    }

    override fun getItem(position: Int): Any {
        return food[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return food.size
    }
}