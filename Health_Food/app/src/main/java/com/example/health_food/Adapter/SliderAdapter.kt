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
import com.bumptech.glide.Glide
import com.example.health_food.R
import com.example.health_food.model.Recipe

class SliderAdapter(val list:ArrayList<Recipe>, val ingredient: ArrayList<String>, val url: String): PagerAdapter() {
    private var mContext: Context?=null

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=LayoutInflater.from(container.context).inflate(R.layout.item_slide, container, false)
        if(position != 0){
            view.findViewById<ConstraintLayout>(R.id.slide_recipe).visibility = View.GONE
            view.findViewById<LinearLayout>(R.id.slide_menu).visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.recipe_older).text = position.toString()
            view.findViewById<TextView>(R.id.recipe_txt).text = list.get(position-1).course
            Glide.with(container.context)
                .load("http://192.168.137.1:3000/images/" + url)
                .into(view.findViewById(R.id.recipe_img))
        }
        else{
            view.findViewById<ConstraintLayout>(R.id.slide_recipe).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.slide_menu).visibility = View.GONE
            var ingredientTxt: String = ""
            var cnt = 1;
            println("여기 ${url}")
            for(i in ingredient){
                if(cnt % 2 == 0){
                    ingredientTxt = ingredientTxt + "\t\t\t\t\t\t\t\t\t" + i + "\n"
                }
                else{
                    ingredientTxt = ingredientTxt + i
                }
                cnt++
            }
            view.findViewById<TextView>(R.id.ingredient_txt).text = ingredientTxt
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getCount(): Int {
        return list.size + 1
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}