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
                    if(mon_kacl == 0) { total_kacl += eatbinding?.monKacl.text.toString().toInt() }
                    else {
                        
                    }
                    eatbinding?.todayKacl.text = total_kacl.toString()
                }
                else Toast.makeText(activity, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            2 -> {
                if(!(eatbinding?.lun?.text.toString() == "") && !(eatbinding?.lunKacl?.text.toString() == "")){
                    total_kacl += eatbinding?.lunKacl.text.toString().toInt()
                    eatbinding?.todayKacl.text = total_kacl.toString()
                }
                else Toast.makeText(activity, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                if(!(eatbinding?.din?.text.toString() == "") && !(eatbinding?.dinKacl?.text.toString() == "")){
                    total_kacl += eatbinding?.dinKacl.text.toString().toInt()
                    eatbinding?.todayKacl.text = total_kacl.toString()
                }
                else Toast.makeText(activity, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}