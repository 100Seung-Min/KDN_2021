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
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat

class Diary : Fragment() {

    var entry_chart:ArrayList<Entry> = ArrayList()
    var day_of_weak = ArrayList<String>()
    var ilinedataset = ArrayList<ILineDataSet>()
    var weight:Float = 0F
    var day:Float = 0F
    var cal_day:String? = null
    private var mainbinding: FragmentDiaryBinding? = null
    lateinit var linedata: LineData
    lateinit var linedataset: LineDataSet
    var xAxis: XAxis? = null
    var text:ArrayList<Diary> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainbinding =  FragmentDiaryBinding.inflate(inflater, container, false)
        val editText = mainbinding?.bodyWeight
        cal_day = SimpleDateFormat("yyyy/M/d").format(Calendar.getInstance().time)
        viewCalendar(cal_day!!, "")
        mainbinding?.calendar?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            cal_day = year.toString() + "/" + (month + 1).toString() + "/" + dayOfMonth.toString()
            viewCalendar(cal_day!!, "")
        }
        mainbinding?.btn?.setOnClickListener {
            if(!editText?.text.toString().equals("")){
                weight = editText?.text.toString().toFloat()
                day = SimpleDateFormat("d").format(Calendar.getInstance().time).toFloat()
                lineChart()
            }
            else Toast.makeText(activity , "몸무게를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        return mainbinding!!.root
    }

    fun viewCalendar(day: String, savetxt: String){
        val inputTxt = mainbinding?.inputTxt
        val viewTxt = mainbinding?.viewTxt
        val delBtn = mainbinding?.delBtn
        val editBtn = mainbinding?.editBtn
        val inputBtn = mainbinding?.inputBtn
        inputTxt?.setText(savetxt)
        for (i in 0..text.size - 1 step(1))
        {
            if(text.get(i).day.equals(day)){
                inputTxt?.visibility = View.INVISIBLE
                viewTxt?.visibility = View.VISIBLE
                delBtn?.visibility = View.VISIBLE
                editBtn?.visibility = View.VISIBLE
                inputBtn?.visibility = View.INVISIBLE
                viewTxt?.text = text.get(i).text
                break
            }
            else{
                inputTxt?.visibility = View.VISIBLE
                viewTxt?.visibility = View.INVISIBLE
                delBtn?.visibility = View.INVISIBLE
                editBtn?.visibility = View.INVISIBLE
                inputBtn?.visibility = View.VISIBLE
            }
        }
        inputBtn?.setOnClickListener {
            text.add(Diary(day = day, text = inputTxt?.text.toString()))
            viewCalendar(day, "")
        }
        editBtn?.setOnClickListener {
            inputTxt?.visibility = View.VISIBLE
            viewTxt?.visibility = View.INVISIBLE
            delBtn?.visibility = View.INVISIBLE
            editBtn?.visibility = View.INVISIBLE
            inputBtn?.visibility = View.VISIBLE
            val num = text.size - 1
            var I = 0
            for(i in 0..num step(1)){
                if(text.get(i).day.equals(day)) {
                    I = i
                }
            }
            text.removeAt(I)
            viewCalendar(day, viewTxt?.text.toString())
        }
        delBtn?.setOnClickListener {
            inputTxt?.visibility = View.VISIBLE
            viewTxt?.visibility = View.INVISIBLE
            delBtn?.visibility = View.INVISIBLE
            editBtn?.visibility = View.INVISIBLE
            inputBtn?.visibility = View.VISIBLE
            val num = text.size - 1
            var I = 0
            for(i in 0..num step(1)){
                if(text.get(i).day.equals(day)) {
                    I = i
                }
            }
            text.removeAt(I)
            viewCalendar(day, "")
        }
    }

    fun lineChart(){
        addChart()
        addLabel()
        linedataset = LineDataSet(entry_chart, "나의 몸무게")
        ilinedataset.add(linedataset)
        linedata = LineData(linedataset)
        linedataset.setColor(Color.BLACK)
        linedataset.setCircleColor(Color.BLACK)
        mainbinding?.chart?.data = (linedata)
        mainbinding?.chart?.axisRight?.setDrawLabels(false)
        mainbinding?.chart?.axisRight?.setDrawGridLines(false)
        mainbinding?.chart?.axisLeft?.setAxisMaxValue(100f)
        mainbinding?.chart?.axisLeft?.setAxisMinValue(60f)
        mainbinding?.chart?.legend?.isEnabled = false
        xAxis = mainbinding?.chart?.xAxis
        xAxis?.setDrawLabels(true)
        xAxis?.valueFormatter = IndexAxisValueFormatter(day_of_weak)
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
    }
    fun addChart(){
        entry_chart.clear()
        entry_chart.add(Entry(day-6, 70f))
        entry_chart.add(Entry(day-5, 68f))
        entry_chart.add(Entry(day-4, 72f))
        entry_chart.add(Entry(day-3, 65f))
        entry_chart.add(Entry(day-2, 64f))
        entry_chart.add(Entry(day-1, 64f))
        entry_chart.add(Entry(day,weight))
    }
    fun addLabel(){
        day_of_weak.clear()
        day_of_weak.add("${day.toInt() - 6}일")
        day_of_weak.add("${day.toInt() - 5}일")
        day_of_weak.add("${day.toInt() - 4}일")
        day_of_weak.add("${day.toInt() - 3}일")
        day_of_weak.add("${day.toInt() - 2}일")
        day_of_weak.add("${day.toInt() - 1}일")
        day_of_weak.add("${day.toInt()}일")
    }
}