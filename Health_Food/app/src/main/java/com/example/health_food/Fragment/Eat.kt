package com.example.health_food.Fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.health_food.R
import com.example.health_food.databinding.FragmentEatBinding
import com.example.health_food.model.Food

class Eat : Fragment() {

    lateinit var eatbinding: FragmentEatBinding
    var total_kacl: Int = 0
    var mon_kacl = 0
    var lun_kacl = 0
    var din_kacl = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        eatbinding = FragmentEatBinding.inflate(inflater, container, false)
        eatbinding?.monSubmit?.setOnClickListener {viewKacl(1)}
        eatbinding?.lunSubmit?.setOnClickListener {viewKacl(2)}
        eatbinding?.dinSubmit?.setOnClickListener {viewKacl(3)}
        return eatbinding!!.root
    }

    fun viewKacl(i: Int){
        when(i){
            1 -> {
                if(!(eatbinding?.mon?.text.toString() == "") && !(eatbinding?.monKacl?.text.toString() == "")){
                    if(mon_kacl == 0) {
                        mon_kacl += eatbinding?.monKacl.text.toString().toInt()
                        total_kacl += mon_kacl
                    }
                    else {
                        total_kacl -= mon_kacl
                        mon_kacl = eatbinding?.monKacl.text.toString().toInt()
                        total_kacl += mon_kacl
                    }
                    eatbinding?.todayKacl.text = total_kacl.toString() + "kacl"
                }
                else Toast.makeText(activity, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            2 -> {
                if(!(eatbinding?.lun?.text.toString() == "") && !(eatbinding?.lunKacl?.text.toString() == "")){
                    if(lun_kacl == 0) {
                        lun_kacl += eatbinding?.lunKacl.text.toString().toInt()
                        total_kacl += lun_kacl
                    }
                    else {
                        total_kacl -= lun_kacl
                        lun_kacl = eatbinding?.lunKacl.text.toString().toInt()
                        total_kacl += lun_kacl
                    }
                    eatbinding?.todayKacl.text = total_kacl.toString() + "kacl"
                }
                else Toast.makeText(activity, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                if(!(eatbinding?.din?.text.toString() == "") && !(eatbinding?.dinKacl?.text.toString() == "")){
                    if(din_kacl == 0) {
                        din_kacl += eatbinding?.dinKacl.text.toString().toInt()
                        total_kacl += din_kacl
                    }
                    else {
                        total_kacl -= din_kacl
                        din_kacl = eatbinding?.dinKacl.text.toString().toInt()
                        total_kacl += din_kacl
                    }
                    eatbinding?.todayKacl.text = total_kacl.toString() + "kacl"
                }
                else Toast.makeText(activity, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}