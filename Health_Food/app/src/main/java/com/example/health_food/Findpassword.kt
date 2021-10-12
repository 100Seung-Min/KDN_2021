package com.example.health_food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.health_food.databinding.ActivityFindpasswordBinding

class Findpassword : AppCompatActivity() {

    val binding by lazy { ActivityFindpasswordBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.inputIdComplete.setOnClickListener {
            binding.inputIdLayout.visibility = View.GONE
            binding.pushEmailLayout.visibility = View.VISIBLE
        }
    }
}