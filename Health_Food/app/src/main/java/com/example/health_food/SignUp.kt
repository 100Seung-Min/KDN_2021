package com.example.health_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.health_food.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.signUpBtn.setOnClickListener {
            if (binding.emailInput.text!!.isNullOrEmpty() ||
                    binding.idInput.text!!.isNullOrEmpty() ||
                    binding.nickInput.text!!.isNullOrEmpty() ||
                    binding.pwInput.text!!.isNullOrEmpty() ||
                    binding.pwInputCheck.text!!.isNullOrEmpty()){
                Toast.makeText(this, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}