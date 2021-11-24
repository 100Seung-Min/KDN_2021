package com.example.health_food.Fragment

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.health_food.Adapter.RecyclerViewAdapter
import com.example.health_food.Cook
import com.example.health_food.MainActivity
import com.example.health_food.R
import com.example.health_food.databinding.FragmentRefrigeratorBinding
import com.example.health_food.model.Food
import com.example.health_food.model.Refrigerator
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class Refrigerator(val userId: String) : Fragment() {
    private var binding: FragmentRefrigeratorBinding? = null
    var itemlist: ArrayList<Refrigerator> = ArrayList()
    var selectlist: ArrayList<Refrigerator> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRefrigeratorBinding.inflate(inflater, container, false)
        initlist()
        binding?.addMaterial?.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.frame_1, Search(userId))
                    ?.commit()
        }
        binding?.startCook?.setOnClickListener {
            if(selectlist.size != 0){
                val id = ArrayList<Int>()
                for(i in selectlist){
                    id.add(i.ingredient)
                }
                val intent = Intent(context, Cook::class.java)
                intent.putExtra("userId", userId)
                intent.putExtra("FoodId", id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
            }
        }
        val adapter = RecyclerViewAdapter(itemlist, context, selectlist){
            var mode = 1
            for(i in selectlist){
                if(i.equals(it)){
                    mode = 2
                }
            }
            if(mode == 2){
                selectlist.remove(it)
            }
            else selectlist.add(it)
            view_recyclerview()
        }
        binding?.foodRefrigerator?.adapter = adapter
        binding?.foodRefrigerator?.layoutManager = GridLayoutManager(context, 3)
        binding?.foodRefrigerator?.addItemDecoration(HorizontalItemDecorator(30))
        binding?.refrigeratorDeleteBtn?.setOnClickListener {
            val id = ArrayList<Int>()
            val ing = ArrayList<Int>()
            for(i in selectlist){
                id.add(i.id)
                ing.add(i.ingredient)
            }
            RetrofitClient.api.postIngredientDelete(userId, id, ing).enqueue(object : retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    intent()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("여기 오류 ${t}")
                }
            })
        }
        return binding!!.root
    }

    fun initlist() {
        RetrofitClient.api.postUserFridge(userId).enqueue(object : retrofit2.Callback<ArrayList<Refrigerator>>{
            override fun onResponse(call: Call<ArrayList<Refrigerator>>, response: Response<ArrayList<Refrigerator>>) {
                for(i in response.body()!!){
                    itemlist.add(i)
                }
                view_recyclerview()
            }

            override fun onFailure(call: Call<ArrayList<Refrigerator>>, t: Throwable) {
                println("여기 오류 ${t}")
            }
        })
    }

    inner class HorizontalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = divHeight
            outRect.right = divHeight
        }
    }
    fun view_recyclerview(){
        if(selectlist.size == 0){
            binding?.refrigeratorDeleteBtn?.visibility = View.GONE
        }
        else{
            binding?.refrigeratorDeleteBtn?.visibility = View.VISIBLE
        }
        val adapter = RecyclerViewAdapter(itemlist, context, selectlist){
            var mode = 1
            for(i in selectlist){
                if(i.equals(it)){
                    mode = 2
                }
            }
            if(mode == 2){
                selectlist.remove(it)
            }
            else selectlist.add(it)
            view_recyclerview()
        }
        binding?.foodRefrigerator?.adapter = adapter
    }
    fun intent(){
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("mode", 1)
        startActivity(intent)
    }
}