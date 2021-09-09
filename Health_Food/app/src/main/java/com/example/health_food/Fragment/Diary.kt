package com.example.health_food.Fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import com.example.health_food.databinding.FragmentDiaryBinding
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.typeOf

class Diary : Fragment() {

    var entry_chart = ArrayList<Entry>()
    var ilinedataset = ArrayList<ILineDataSet>()
    var weight:Float = 0F
    var day:Float = 0F
    var cal_day:String? = null
    private var mainbinding: FragmentDiaryBinding? = null
    lateinit var linedata: LineData
    lateinit var linedataset: LineDataSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainbinding =  FragmentDiaryBinding.inflate(inflater, container, false)
        val editText = mainbinding?.bodyWeight
        mainbinding?.calendar?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            cal_day = year.toString() + "/" + month.toString() + "/" + dayOfMonth.toString()
            viewCalendar(cal_day!!)
        }
        mainbinding?.btn?.setOnClickListener {
            if(!editText?.text.toString().equals("")){
                weight = editText?.text.toString().toFloat()
                day = Calendar.DAY_OF_MONTH.toFloat() + 1
                lineChart()
            }
            else Toast.makeText(activity , "몸무게를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        return mainbinding!!.root
    }

    fun viewCalendar(day: String){
        val caltxt = mainbinding?.calTxt
        caltxt?.text = day
    }

    fun lineChart(){
        println(day)
        println(weight)
        entry_chart.add(Entry(day,weight))
        linedataset = LineDataSet(entry_chart, "나의 몸무게")
        ilinedataset.add(linedataset)
        linedata = LineData(ilinedataset)
        linedataset.setColor(Color.BLACK)
        linedataset.setCircleColor(Color.BLACK)
        mainbinding?.chart?.data = (linedata)
    }
}