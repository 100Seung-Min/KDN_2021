package com.example.health_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.health_food.databinding.ActivityAddCommunityImageBinding

class AddCommunityImage : AppCompatActivity() {

    val binding by lazy { ActivityAddCommunityImageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addImg.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent, 0)
        }
    }
}