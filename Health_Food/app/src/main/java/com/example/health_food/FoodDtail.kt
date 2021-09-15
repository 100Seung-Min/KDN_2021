package com.example.health_food

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.health_food.databinding.ActivityFoodDtailBinding
import com.example.health_food.model.RecommendDTO
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodDtail : AppCompatActivity() {

    val mainbinding by lazy { ActivityFoodDtailBinding.inflate(layoutInflater) }
    var imgurl:String? = null
    var foodname:String? = null
    var tansu: Float? = null
    var nate: Float? = null
    var jiban: Float? = null
    var vitamin: Float? = null
    var danbak: Float? = null
    val values = ArrayList<PieEntry>()
    val description:Description = Description()
    lateinit var dataset: PieDataSet
    lateinit var data: PieData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainbinding!!.root)
        imgurl = intent.getStringExtra("imgurl")
        foodname = intent.getStringExtra("foodname")
        tansu = intent.getFloatExtra("tansu", 0f)
        nate = intent.getFloatExtra("nate", 0f)
        jiban = intent.getFloatExtra("jiban", 0f)
        vitamin = intent.getFloatExtra("vitamin", 0f)
        danbak = intent.getFloatExtra("danbak", 0f)
        Glide.with(this)
            .load(imgurl)
            .into(mainbinding.dtailFoodImg)
        mainbinding.dtailFoodTxt.text = foodname
        mainbinding?.backBtn?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        mainbinding?.foodRecipeTxt?.text = "안녕\n하세요"
        val piechart = mainbinding?.piechart


        piechart.setUsePercentValues(true)
        piechart.description.isEnabled = false
        piechart.setExtraOffsets(5f,10f,5f,5f)
        piechart.dragDecelerationFrictionCoef = 0.95f
        piechart.isDrawHoleEnabled = false
        piechart.setHoleColor(R.color.white)
        piechart.transparentCircleRadius = 61f
        values.add(PieEntry(tansu!!, "탄수화물"))
        values.add(PieEntry(nate!!, "나트륨"))
        values.add(PieEntry(jiban!!, "지방"))
        values.add(PieEntry(vitamin!!, "비타민"))
        values.add(PieEntry(danbak!!, "단백질"))
        description.text = "영양성분표"
        description.textSize = 15f
        piechart.description = description
        dataset = PieDataSet(values, "성분이름")
        dataset.sliceSpace = 3f
        dataset.selectionShift = 5f
        dataset.setColors(*ColorTemplate.PASTEL_COLORS)

        data = PieData((dataset))
        data.setValueTextSize(10f)
        data.setValueTextColor(R.color.black)
        piechart.data = data
    }
}