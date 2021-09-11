package com.example.health_food.Fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.health_food.databinding.FragmentDiaryBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.*
import kotlin.collections.ArrayList
import com.example.health_food.model.Diary

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
            cal_day = year.toString() + "/" + (month + 1).toString() + "/" + dayOfMonth.toString()
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
        val inputTxt = mainbinding?.inputTxt
        val viewTxt = mainbinding?.viewTxt
        val delBtn = mainbinding?.delBtn
        val editBtn = mainbinding?.editBtn
        val inputBtn = mainbinding?.inputBtn
        val text:ArrayList<Diary> = ArrayList()
        text.add(Diary(day = "2021/9/11", text = "안녕하세요"))
        for (i in 0..text.size - 1 step(1))
        {
            if(text.get(i).day.equals(day)){
                inputTxt?.visibility = View.INVISIBLE
                viewTxt?.visibility = View.VISIBLE
                delBtn?.visibility = View.VISIBLE
                editBtn?.visibility = View.VISIBLE
                inputBtn?.visibility = View.INVISIBLE
                viewTxt?.text = text.get(i).text
            }
            else{
                println("여기" + text.get(i).day)
                println("여기" + day)
                inputTxt?.visibility = View.VISIBLE
                viewTxt?.visibility = View.INVISIBLE
                delBtn?.visibility = View.INVISIBLE
                editBtn?.visibility = View.INVISIBLE
                inputBtn?.visibility = View.VISIBLE
            }
        }
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