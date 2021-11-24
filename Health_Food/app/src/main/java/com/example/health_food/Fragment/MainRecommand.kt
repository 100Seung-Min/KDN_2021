package com.example.health_food.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.health_food.FoodDtail
import com.example.health_food.databinding.FragmentMainRecommandBinding
import com.example.health_food.model.Recipe
import com.example.health_food.model.RecommendDTO
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainRecommand(var recommendDTO:ArrayList<RecommendDTO>) : Fragment() {

    private var mainbinding: FragmentMainRecommandBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainbinding = FragmentMainRecommandBinding.inflate(inflater, container, false)
        view_recommend(recommendDTO)

        mainbinding?.mainRecommand?.setOnClickListener {
            callRetrofit(recommendDTO[0])
        }

        mainbinding?.breakfastFirst?.setOnClickListener {
            callRetrofit(recommendDTO[1])
        }
        mainbinding?.breakfastSecond?.setOnClickListener {
            callRetrofit(recommendDTO[2])
        }
        mainbinding?.breakfastThird?.setOnClickListener {
            callRetrofit(recommendDTO[3])
        }
        mainbinding?.lunchFirst?.setOnClickListener {
            callRetrofit(recommendDTO[4])
        }
        mainbinding?.lunchSecond?.setOnClickListener {
            callRetrofit(recommendDTO[5])
        }
        mainbinding?.lunchThird?.setOnClickListener {
            callRetrofit(recommendDTO[6])
        }
        mainbinding?.dinnerFirst?.setOnClickListener {
            callRetrofit(recommendDTO[7])
        }
        mainbinding?.dinnerSecond?.setOnClickListener {
            callRetrofit(recommendDTO[8])
        }
        mainbinding?.dinnerThird?.setOnClickListener {
            callRetrofit(recommendDTO[9])
        }
        return mainbinding?.root
    }

    fun view_recommend(recommendDTO: ArrayList<RecommendDTO>){
        var i = 0;
        for(recommend in recommendDTO){
            when(i) {
                0 -> {
                    mainbinding?.content?.text = recommend.introduce
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.mainRecommand!!)
                        i++
                    }
                }
                1 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.breakfastFirst!!)
                        i++
                    }
                }
                2 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.breakfastSecond!!)
                        i++
                    }
                }
                3 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.breakfastThird!!)
                        i++
                    }
                }
                4 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.lunchFirst!!)
                        i++
                    }
                }
                5 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.lunchSecond!!)
                        i++
                    }
                }
                6 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.lunchThird!!)
                        i++
                    }
                }
                7 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.dinnerFirst!!)
                        i++
                    }
                }
                8 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.dinnerSecond!!)
                        i++
                    }
                }
                9 -> {
                    if(recommend.url != null){
                        show_recommend_image(recommend.url!!, mainbinding?.dinnerThird!!)
                        i++
                    }
                }
            }
        }
    }

    fun show_recommend_image(url: String, view: ImageView){
        Glide.with(this)
            .load("http://192.168.137.1:3000/images/" + url)
            .into(view)
    }

    fun callRetrofit(recommend: RecommendDTO) {
        val id = recommend.id.toString()
        var ingredient = ArrayList<String>()
        RetrofitClient.api.postIngredient(id)
            .enqueue(object : retrofit2.Callback<ArrayList<String>> {
                override fun onResponse(
                    call: Call<ArrayList<String>>,
                    response: Response<ArrayList<String>>
                ) {
                    for (i in response.body()!!) {
                        ingredient.add(i)
                    }
                    intent_fooddtail(recommend, ingredient)
                }

                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    println("여기 오류 ${t}")
                }
            })
    }

    fun intent_fooddtail(recommend: RecommendDTO, ingredient: ArrayList<String>){
        val intent = Intent(activity, FoodDtail::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        intent.putExtra("imgurl", recommend.url)
        intent.putExtra("foodname", recommend.name)
        intent.putExtra("content", recommend.introduce)
        intent.putExtra("ingredient", ingredient)
        intent.putExtra("recipe-id", recommend.id.toString())
        startActivity(intent)
    }
}