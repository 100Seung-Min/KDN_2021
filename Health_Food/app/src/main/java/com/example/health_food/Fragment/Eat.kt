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
import com.example.health_food.R
import com.example.health_food.model.Food

class Eat : Fragment() {
    val foodsearch: ArrayList<Food> = ArrayList()
    var foodlist:ArrayList<Food> = ArrayList()
//    val listviewadapter = ListViewAdapter(activity!!, foodsearch)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "채수빈"))
        foodsearch.add(Food(fooditem = "채수빈"))
        foodlist.addAll(foodsearch)
//        view?.findViewById<ListView>(R.id.search_food_lisst)?.adapter = listviewadapter
        view?.findViewById<EditText>(R.id.search_food_edit)?.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                val text = view?.findViewById<EditText>(R.id.search_food_edit)?.text.toString()
//            search(text)
                println("여기" + text)
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }
        })
        return inflater.inflate(R.layout.fragment_eat, container, false)
    }
//    private fun search(text:String){
//        foodsearch.clear()
//        if (text.length == 0) {
//            foodsearch.addAll(foodlist)
//        }
//        else{
//            for(i in 0..foodlist.size - 1 step (1)){
//                if(foodlist.get(i).toString().contains(text)){
//                    foodsearch.add(foodlist.get(i))
//                }
//            }
//        }
//        listviewadapter.notifyDataSetChanged()
//    }
//    inner class ListViewAdapter(val context: Context, val food: ArrayList<Food>): BaseAdapter(){
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            val view: View = LayoutInflater.from(context).inflate(R.layout.item_food, null)
//            val foodItem = view.findViewById<TextView>(R.id.item_food_txt)
//            val foodname = food[position]
//            foodItem.text = foodname.fooditem
//            return view
//        }
//
//        override fun getItem(position: Int): Any {
//            return food[position]
//        }
//
//        override fun getItemId(position: Int): Long {
//            return 0
//        }
//
//        override fun getCount(): Int {
//            return food.size
//        }
//    }
}