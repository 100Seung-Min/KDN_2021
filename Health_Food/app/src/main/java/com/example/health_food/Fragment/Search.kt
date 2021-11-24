package com.example.health_food.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.health_food.Adapter.ListViewAdapter
import com.example.health_food.Adapter.RecyclerViewAdapter2
import com.example.health_food.MainActivity
import com.example.health_food.R
import com.example.health_food.model.Food
import com.example.health_food.retrofit.RetrofitClient
import okhttp3.RequestBody
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class Search(val userId: String) : Fragment() {
    var foodsearch: ArrayList<Food> = ArrayList()
    var foodlist:ArrayList<Food> = ArrayList()
    var select_food:ArrayList<Food> = ArrayList()
    lateinit var viewitem: View
    lateinit var listviewadapter:ListViewAdapter
    lateinit var selectadapter: RecyclerViewAdapter2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewitem = inflater.inflate(R.layout.fragment_search, container, false)
        initlist()
        listviewadapter = ListViewAdapter(foodsearch)
        viewitem?.findViewById<ListView>(R.id.search_food_list)?.adapter = listviewadapter
        viewitem?.findViewById<ListView>(R.id.search_food_list)?.setOnItemClickListener { parent, view, position, id ->
            viewitem.findViewById<Button>(R.id.add_material_btn).visibility = View.VISIBLE
            if(select_food.contains(foodsearch[position]))
            else
                select_food.add(foodsearch[position])
            if(select_food.size != 0){
                viewitem?.findViewById<RecyclerView>(R.id.select_item)?.visibility = View.VISIBLE
                select_recyclerview()
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

        viewitem?.findViewById<Button>(R.id.add_material_btn).setOnClickListener {
            var foodId = ArrayList<Int>()
            for(i in select_food){
                foodId.add(i.id)
            }
            RetrofitClient.api.postFrige(userId, foodId).enqueue(object : retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    println("여기 ${response.body()}")
                    main_intent()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("여기 오류 ${t}")
                }

            })
        }
        return viewitem
    }
    private fun search(text:String){
        foodsearch.clear()
        if (text.length == 0) {
            foodsearch.addAll(foodlist)
        }
        else{
            for(i in 0..foodlist.size - 1 step (1)){
                if(foodlist.get(i).name.contains(text)){
                    foodsearch.add(foodlist.get(i))
                }
            }
        }
        listviewadapter.notifyDataSetChanged()
    }
    fun main_intent(){
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("mode", 1)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
    private fun initlist(){
        RetrofitClient.api.postIngredientList().enqueue(object : retrofit2.Callback<ArrayList<Food>>{
            override fun onResponse(
                call: Call<ArrayList<Food>>,
                response: Response<ArrayList<Food>>
            ) {
                foodsearch.clear()
                for(i in response.body()!!){
                    foodsearch!!.add(i)
                }
                addList()
            }

            override fun onFailure(call: Call<ArrayList<Food>>, t: Throwable) {
                println("여기 오류 ${t}")
            }

        })
    }
    private fun select_recyclerview(){
        selectadapter = RecyclerViewAdapter2(select_food) { item ->
            select_food.remove(item)
            select_recyclerview()
            if(select_food.size == 0){
                viewitem.findViewById<RecyclerView>(R.id.select_item).visibility = View.GONE
                viewitem.findViewById<Button>(R.id.add_material_btn).visibility = View.GONE
            }
        }
        viewitem.findViewById<RecyclerView>(R.id.select_item).adapter = selectadapter
        viewitem.findViewById<RecyclerView>(R.id.select_item).layoutManager = GridLayoutManager(context, 3)
    }

    fun addList(){
        foodlist.addAll(foodsearch)
        listviewadapter = ListViewAdapter(foodsearch)
        viewitem?.findViewById<ListView>(R.id.search_food_list)?.adapter = listviewadapter
    }
}