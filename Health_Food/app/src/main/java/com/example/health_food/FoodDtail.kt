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
import com.example.health_food.model.Recipe
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class FoodDtail : AppCompatActivity() {

    val mainbinding by lazy { ActivityFoodDtailBinding.inflate(layoutInflater)}
    var imgurl:String? = null
    var foodname:String? = null
    var content: String? = null
    var ingredient: ArrayList<String>? = ArrayList()
    var dots: Array<TextView?> = emptyArray()
    var list:ArrayList<Recipe>? = ArrayList()
    var recipeId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainbinding!!.root)
        imgurl = intent.getStringExtra("imgurl")
        foodname = intent.getStringExtra("foodname")
        content = intent.getStringExtra("content")
        recipeId = intent.getStringExtra("recipe-id")
        ingredient = intent.getStringArrayListExtra("ingredient")
        callRetrofit()
        Glide.with(this)
                .load("http://192.168.137.1:3000/images/" + imgurl)
                .into(mainbinding.dtailFoodImg)
        if(ingredient == null){
            ingredient = ArrayList()
        }
        mainbinding.viewPager.adapter = SliderAdapter(list!!, ingredient!!, imgurl!!)
        mainbinding.dtailFoodName.text = foodname
        mainbinding.dtailFoodBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
            finish()
        }
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

    fun callRetrofit(){
        RetrofitClient.api.postRecipe(recipeId!!).enqueue(object : retrofit2.Callback<ArrayList<Recipe>>{
            override fun onResponse(
                call: Call<ArrayList<Recipe>>,
                response: Response<ArrayList<Recipe>>
            ) {
                for(i in response.body()!!){
                    list!!.add(i)
                }
                draw()
                dataChanged()
            }

            override fun onFailure(call: Call<ArrayList<Recipe>>, t: Throwable) {
                println("여기 오류 ${t}")
            }
        })
    }

    fun draw(){
        dots = arrayOfNulls<TextView>(list!!.size + 1)
        Indicator()
    }

    fun dataChanged(){
        mainbinding.viewPager.adapter = SliderAdapter(list!!, ingredient!!, imgurl!!)
    }
}