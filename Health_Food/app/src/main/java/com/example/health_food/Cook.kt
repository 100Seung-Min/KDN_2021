package com.example.health_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.health_food.Adapter.RecyclerViewAdapter4
import com.example.health_food.databinding.ActivityCookBinding
import com.example.health_food.model.FrigeRecipe
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class Cook : AppCompatActivity() {
    val binding by lazy {ActivityCookBinding.inflate(layoutInflater)}
    var userId: String? = null
    var foodId: ArrayList<Int>? = null
    var ingredient = ArrayList<String>()
    var itemlist: ArrayList<FrigeRecipe> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        userId = intent.getStringExtra("userId")
        foodId = intent.getIntegerArrayListExtra("FoodId")
        RetrofitClient.api.postfrigeRecipe(userId!!, foodId!!).enqueue(object : retrofit2.Callback<ArrayList<FrigeRecipe>>{
            override fun onResponse(
                call: Call<ArrayList<FrigeRecipe>>,
                response: Response<ArrayList<FrigeRecipe>>
            ) {
                for(i in response.body()!!){
                    itemlist.add(i)
                }
                recyclerview()
            }

            override fun onFailure(call: Call<ArrayList<FrigeRecipe>>, t: Throwable) {
                println("여기 오류 ${t}")
            }
        })
        val adapter = RecyclerViewAdapter4(itemlist, this){
            RetrofitClient.api.postIngredient(it.id.toString())
                .enqueue(object : retrofit2.Callback<ArrayList<String>> {
                    override fun onResponse(
                        call: Call<ArrayList<String>>,
                        response: Response<ArrayList<String>>
                    ) {
                        for (i in response.body()!!) {
                            ingredient.add(i)
                        }
                        intent(it)
                    }

                    override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                        println("여기 오류 ${t}")
                    }
                })
            intent(it)
        }
        binding.recipeRecyclerview.adapter = adapter
        binding.recipeRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    fun recyclerview(){
        binding.findRecipeTxt.text = "${itemlist.size}개의 레시피를 찾았어요"
        val adapter = RecyclerViewAdapter4(itemlist, this){
            RetrofitClient.api.postIngredient(it.id.toString())
                .enqueue(object : retrofit2.Callback<ArrayList<String>> {
                    override fun onResponse(
                        call: Call<ArrayList<String>>,
                        response: Response<ArrayList<String>>
                    ) {
                        for (i in response.body()!!) {
                            ingredient.add(i)
                        }
                        intent(it)
                    }

                    override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                        println("여기 오류 ${t}")
                    }
                })
            intent(it)
        }
        binding.recipeRecyclerview.adapter = adapter
    }

    fun intent(it: FrigeRecipe){
        val intent = Intent(this, FoodDtail::class.java)
        intent.putExtra("imgurl", it.url)
        intent.putExtra("foodname", it.name)
        intent.putExtra("content", it.introduce)
        intent.putExtra("recipe-id", it.id.toString())
        intent.putExtra("ingredient", ingredient)
        startActivity(intent)
        finish()
    }
}