package com.example.health_food

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
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
    var dots: Array<TextView?> = emptyArray()
    var list:Array<String> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainbinding!!.root)
        initlist()
        dots = arrayOfNulls<TextView>(list!!.size)
        Indicator()
        imgurl = intent.getStringExtra("imgurl")
        foodname = intent.getStringExtra("foodname")
        Glide.with(this)
                .load(imgurl)
                .into(mainbinding.dtailFoodImg)
        mainbinding.dtailFoodBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        mainbinding.viewPager.adapter = SliderAdapter(list)
        mainbinding.dtailFoodName.text = foodname
        mainbinding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                dotselect(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun Indicator() {
        for(i in 0..dots.size - 1){
            dots[i] = TextView(this)
            dots[i]?.setText(Html.fromHtml("&#9679;"))
            dots[i]?.setTextSize(10f)
            if(i == 0){
                dots[i]?.setTextColor(Color.parseColor("#00FF29"))
            }
            else{
                dots[i]?.setTextColor(Color.parseColor("#000000"))
            }
            mainbinding.dotPager.addView(dots[i])
        }
    }

    private fun initlist(){
        list = list?.plus("안녕하세요")
        list = list?.plus("안녕히가세요")
    }

    private fun dotselect(position: Int){
        for(i in 0..dots.size-1){
            if(i == position){
                dots[i]?.setTextColor(Color.parseColor("#00FF29"))
            }
            else{
                dots[i]?.setTextColor(Color.parseColor("#000000"))
            }
        }
    }
}