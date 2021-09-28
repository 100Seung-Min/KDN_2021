package com.example.health_food

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health_food.databinding.FragmentLoginBinding

class Login: AppCompatActivity() {

    val binding by lazy { FragmentLoginBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {
            if(binding.emailEdit.text!!.isNullOrEmpty() || binding.pwEdit.text!!.isNullOrEmpty()){
                Toast.makeText(this, "두개 다 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, StartActivity::class.java))
                finish()
            }
        }
        binding.signUp.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
    }
}