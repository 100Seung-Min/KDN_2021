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
import com.example.health_food.R
import com.example.health_food.databinding.FragmentMainBinding
import com.example.health_food.databinding.FragmentMainRecommandBinding
import com.example.health_food.model.RecommendDTO

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

        mainbinding?.breakfastFirst?.setOnClickListener {
            intent_fooddtail(recommendDTO[0])
        }
        mainbinding?.breakfastSecond?.setOnClickListener {
            intent_fooddtail(recommendDTO[1])
        }
        mainbinding?.breakfastThird?.setOnClickListener {
            intent_fooddtail(recommendDTO[2])
        }
        mainbinding?.lunchFirst?.setOnClickListener {
            intent_fooddtail(recommendDTO[3])
        }
        mainbinding?.lunchSecond?.setOnClickListener {
            intent_fooddtail(recommendDTO[4])
        }
        mainbinding?.lunchThird?.setOnClickListener {
            intent_fooddtail(recommendDTO[5])
        }
        mainbinding?.dinnerFirst?.setOnClickListener {
            intent_fooddtail(recommendDTO[6])
        }
        mainbinding?.dinnerSecond?.setOnClickListener {
            intent_fooddtail(recommendDTO[7])
        }
        mainbinding?.dinnerThird?.setOnClickListener {
            intent_fooddtail(recommendDTO[8])
        }
        return mainbinding?.root
    }

    fun view_recommend(recommendDTO: ArrayList<RecommendDTO>){
        var i = 0;
        for(recommend in recommendDTO){
            when(i) {
                0 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.breakfastFirst!!)
                    i++
                }
                1 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.breakfastSecond!!)
                    i++
                }
                2 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.breakfastThird!!)
                    i++
                }
                3 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.lunchFirst!!)
                    i++
                }
                4 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.lunchSecond!!)
                    i++
                }
                5 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.lunchThird!!)
                    i++
                }
                6 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.dinnerFirst!!)
                    i++
                }
                7 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.dinnerSecond!!)
                    i++
                }
                8 -> {
                    show_recommend_image(recommend.imgurl!!, mainbinding?.dinnerThird!!)
                    i++
                }
            }
        }
    }

    fun show_recommend_image(url: String, view: ImageView){
        Glide.with(this)
            .load(url)
            .into(view)
    }

    fun intent_fooddtail(recommend: RecommendDTO){
        val intent = Intent(activity, FoodDtail::class.java)
        intent.putExtra("imgurl", recommend.imgurl)
        intent.putExtra("foodname", recommend.foodname)
        startActivity(intent)
    }
}