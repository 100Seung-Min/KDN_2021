package com.example.health_food.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.health_food.R

class SliderAdapter(val list:Array<String>): PagerAdapter() {
    private var mContext: Context?=null

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=LayoutInflater.from(container.context).inflate(R.layout.item_slide, container, false)
        if(position != 0){
            view.findViewById<ConstraintLayout>(R.id.slide_recipe).visibility = View.GONE
            view.findViewById<LinearLayout>(R.id.slide_menu).visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.recipe_older).text = (position + 1).toString()
            view.findViewById<TextView>(R.id.recipe_txt).text = list[position]
        }
        else{
            view.findViewById<ConstraintLayout>(R.id.slide_recipe).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.slide_menu).visibility = View.GONE
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}