package com.example.health_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.health_food.databinding.ActivityAddCommunityImageBinding
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class AddCommunityImage : AppCompatActivity() {

    val binding by lazy { ActivityAddCommunityImageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pref = getSharedPreferences("LOGIN", MODE_PRIVATE)
        val userId = pref.getString("userId", "")

        binding.addImg.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent, 0)
        }

        binding.uploadBtn.setOnClickListener {
            val content = binding.detailTxt.text.toString()
            val tag = binding.hashtagTxt.text.toString()
            RetrofitClient.api.postUpload(userId!!, "안녕", tag, content).enqueue(object : retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    println("여기 ${response.body()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("여기 오류${t}")
                }
            })
        }
    }
}