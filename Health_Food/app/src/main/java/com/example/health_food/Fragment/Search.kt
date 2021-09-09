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
import com.example.health_food.R
import com.example.health_food.model.Food

class Search : Fragment() {
    val foodsearch: ArrayList<Food> = ArrayList()
    var foodlist:ArrayList<Food> = ArrayList()
    lateinit var listviewadapter:ListViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "민도현"))
        foodsearch.add(Food(fooditem = "아이유"))
        foodsearch.add(Food(fooditem = "오종진"))
        foodsearch.add(Food(fooditem = "조현서"))
        foodsearch.add(Food(fooditem = "백승민"))
        foodsearch.add(Food(fooditem = "김성길"))
        foodsearch.add(Food(fooditem = "김동현"))
        foodlist.addAll(foodsearch)
        listviewadapter = ListViewAdapter(foodsearch)
        view?.findViewById<ListView>(R.id.search_food_list)?.adapter = listviewadapter
        view?.findViewById<EditText>(R.id.search_food_edit)?.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = view?.findViewById<EditText>(R.id.search_food_edit)?.text.toString()
                search(text)
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
            }
        })
        return view
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
    inner class ListViewAdapter(val food: ArrayList<Food>): BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_food, null)
            val foodItem = view.findViewById<TextView>(R.id.item_food_txt)
            val foodname = food[position].fooditem
            foodItem.text = foodname
            return view
        }

        override fun getItem(position: Int): Any {
            return food[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return food.size
        }
    }
}