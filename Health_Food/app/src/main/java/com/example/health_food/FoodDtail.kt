package com.example.health_food

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.health_food.Adapter.SliderAdapter
import com.example.health_food.databinding.ActivityFoodDtailBinding
import com.example.health_food.databinding.ActivityMainBinding
import com.example.health_food.model.RecommendDTO
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodDtail : AppCompatActivity() {

    val mainbinding by lazy { ActivityFoodDtailBinding.inflate(layoutInflater)}
    var imgurl:String? = null
    var foodname:String? = null
    var dots:Array<TextView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainbinding!!.root)
        imgurl = intent.getStringExtra("imgurl")
        foodname = intent.getStringExtra("foodname")
        Glide.with(this)
                .load(imgurl)
                .into(mainbinding.dtailFoodImg)
        mainbinding.dtailFoodBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        mainbinding.viewPager.adapter = SliderAdapter()
        Indicator()
        mainbinding.dtailFoodName.text = foodname
    }

    inner class Indicator {
        for(i in 0..10){

        }
    }
}