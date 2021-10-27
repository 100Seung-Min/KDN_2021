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
import androidx.recyclerview.widget.RecyclerView
import com.example.health_food.Adapter.ListViewAdapter
import com.example.health_food.R
import com.example.health_food.model.Food
import org.w3c.dom.Text

class Search : Fragment() {
    val foodsearch: ArrayList<Food> = ArrayList()
    var foodlist:ArrayList<Food> = ArrayList()
    var select_food:ArrayList<String> = ArrayList()
    lateinit var viewitem: View
    lateinit var listviewadapter:ListViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewitem = inflater.inflate(R.layout.fragment_search, container, false)
        initlist()
        foodlist.addAll(foodsearch)
        listviewadapter = ListViewAdapter(foodsearch)
        viewitem?.findViewById<ListView>(R.id.search_food_list)?.adapter = listviewadapter
        viewitem?.findViewById<ListView>(R.id.search_food_list)?.setOnItemClickListener { parent, view, position, id ->
            select_food.add(foodsearch[position].fooditem)
            if(select_food.size != 0){
                Toast.makeText(activity, "${foodsearch[position].fooditem}", Toast.LENGTH_SHORT).show()
                println(select_food[0] + "여기")
                viewitem?.findViewById<RecyclerView>(R.id.select_item)?.visibility = View.VISIBLE
            }
        }
        viewitem?.findViewById<EditText>(R.id.search_food_edit)?.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = viewitem?.findViewById<EditText>(R.id.search_food_edit)?.text.toString()
                search(text)
            }
        })
        return viewitem
    }
    private fun search(text:String){
        foodsearch.clear()
        if (text.length == 0) {
            foodsearch.addAll(foodlist)
        }
        else{
            for(i in 0..foodlist.size - 1 step (1)){
                if(foodlist.get(i).fooditem.contains(text)){
                    foodsearch.add(foodlist.get(i))
                }
            }
        }
        listviewadapter.notifyDataSetChanged()
    }
    fun initlist(){
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "민도현"))
        foodsearch.add(Food(fooditem = "아이유"))
        foodsearch.add(Food(fooditem = "오종진"))
        foodsearch.add(Food(fooditem = "조현서"))
        foodsearch.add(Food(fooditem = "백승민"))
        foodsearch.add(Food(fooditem = "김성길"))
        foodsearch.add(Food(fooditem = "김동현"))
        foodsearch.add(Food(fooditem = "안진형"))
        foodsearch.add(Food(fooditem = "선민재"))
        foodsearch.add(Food(fooditem = "박영재"))
        foodsearch.add(Food(fooditem = "홍세현"))
        foodsearch.add(Food(fooditem = "전승원"))
        foodsearch.add(Food(fooditem = "김형록"))
        foodsearch.add(Food(fooditem = "김준"))
        foodsearch.add(Food(fooditem = "이준"))
    }
}