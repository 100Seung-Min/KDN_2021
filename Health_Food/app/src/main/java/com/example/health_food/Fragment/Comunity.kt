package com.example.health_food.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.health_food.Adapter.RecyclerViewAdapter3
import com.example.health_food.AddCommunityImage
import com.example.health_food.R
import com.example.health_food.databinding.FragmentComunityBinding
import com.example.health_food.model.CommunityDTO
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class Comunity : Fragment() {

    lateinit var binding: FragmentComunityBinding

    var itemlist = ArrayList<CommunityDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentComunityBinding.inflate(inflater, container, false)

        recyclerview()

        RetrofitClient.api.getList().enqueue(object : retrofit2.Callback<ArrayList<CommunityDTO>>{
            override fun onResponse(call: Call<ArrayList<CommunityDTO>>, response: Response<ArrayList<CommunityDTO>>) {
                for(i in response.body()!!){
                    itemlist.add(i)
                    recyclerview()
                }
            }

            override fun onFailure(call: Call<ArrayList<CommunityDTO>>, t: Throwable) {
                println("여기 오류${t}")
            }

        })

        binding.addCommunityBtn.setOnClickListener {
            val intent = Intent(context, AddCommunityImage::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }

        return binding.root
    }

    fun recyclerview(){
        val adapter = RecyclerViewAdapter3(itemlist)

        binding.communityRecyclerview.adapter = adapter
        binding.communityRecyclerview.layoutManager = LinearLayoutManager(context)
    }
}