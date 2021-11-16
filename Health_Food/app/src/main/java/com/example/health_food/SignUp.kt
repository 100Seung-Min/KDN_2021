package com.example.health_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.health_food.databinding.ActivitySignUpBinding
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SignUp : AppCompatActivity() {

    val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater)}
    var isIdOverlap = ""
    var isEmailOverlap = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.idCheckBtn.setOnClickListener {
            RetrofitClient.api.postIdOverlap(binding.idInput.text.toString()).enqueue(object : retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    isIdOverlap = response.body().toString()
                    if(isIdOverlap == "idfail"){
                        massage("중복된 아이디입니다.")
                    }
                    else binding.idCheckBtn.visibility = View.GONE
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("오류 ${t}")
                }

            })
        }
        binding.emailCheckBtn.setOnClickListener {
            RetrofitClient.api.postEmailOverlap(binding.emailInput.toString()).enqueue(object : retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    isEmailOverlap = response.body().toString()
                    if(isEmailOverlap == "emailfail"){
                        massage("중복된 이메일입니다.")
                    }
                    else binding.emailCheckBtn.visibility = View.GONE
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("오류 ${t}")
                }

            })
        }
        binding.signUpBtn.setOnClickListener {
            println("여기${isIdOverlap} + ${isEmailOverlap}")
            if (binding.emailInput.text!!.isNullOrEmpty() ||
                    binding.idInput.text!!.isNullOrEmpty() ||
                    binding.nickInput.text!!.isNullOrEmpty() ||
                    binding.pwInput.text!!.isNullOrEmpty() ||
                    binding.pwInputCheck.text!!.isNullOrEmpty()){
                Toast.makeText(this, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(isIdOverlap == "" ||
                isEmailOverlap == ""){
                Toast.makeText(this, "중복체크를 해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val email = binding.emailInput.text.toString()
                val id = binding.idInput.text.toString()
                val nick = binding.nickInput.text.toString()
                val pw = binding.pwInput.text.toString()
               RetrofitClient.api.postSignUp(id, pw, email, nick).enqueue(object : retrofit2.Callback<String>{
                   override fun onResponse(call: Call<String>, response: Response<String>) {
                       signup()
                   }

                   override fun onFailure(call: Call<String>, t: Throwable) {
                       println("여기 오류 + ${t}")
                   }
               })
            }
        }
    }
    fun signup(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    fun massage(massage: String){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show()
    }
}