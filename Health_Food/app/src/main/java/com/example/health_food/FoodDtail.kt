package com.example.health_food

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.health_food.databinding.ActivityFoodDtailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodDtail : AppCompatActivity() {

    val mainbinding by lazy { ActivityFoodDtailBinding.inflate(layoutInflater) }
    var imgurl:String? = null
    var foodname:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainbinding!!.root)
        imgurl = intent.getStringExtra("imgurl")
        foodname = intent.getStringExtra("foodname")
        Glide.with(this)
            .load(imgurl)
            .into(mainbinding.dtailFoodImg)
        mainbinding.dtailFoodTxt.text = foodname
        mainbinding?.backBtn?.setOnClickListener {
            findViewById<BottomNavigationView>(R.id.navigation).selectedItemId = R.id.home
        }
    }
}