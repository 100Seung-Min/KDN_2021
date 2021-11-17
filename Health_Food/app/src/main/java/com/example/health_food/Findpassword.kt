package com.example.health_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.health_food.databinding.ActivityFindpasswordBinding
import com.example.health_food.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class Findpassword : AppCompatActivity() {

    val binding by lazy { ActivityFindpasswordBinding.inflate(layoutInflater) }
    var password: String = ""
    var code: String = ""
    var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.inputIdComplete.setOnClickListener {
            if(binding.inputIdEdit.text.toString() != ""){
                userId = binding.inputIdEdit.text.toString()
                RetrofitClient.api.postEmailAuth(userId).enqueue(object : retrofit2.Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        code = response.body().toString()
                        println("여기 ${response.body().toString()}")
                        if(code != "searchingFail"){
                            next()
                        }
                        else if(code == "searchingFail"){
                            makeText("일치하는 아이디가 없습니다")
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("여기오류 ${t}")
                    }
                })
            }
            else
                Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        binding.codeFinishBtn.setOnClickListener {
            if(binding.editPw.text.toString() == code){
                binding.pushEmailLayout.visibility = View.GONE
                binding.changePwLayout.visibility = View.VISIBLE
            }
            else
                makeText("암호코드가 일치하지 않습니다")
        }
        binding.loginBtn.setOnClickListener {
            if(binding.pwChangeEdit.text.toString() == binding.pwCheckEdit.text.toString()){
                RetrofitClient.api.postPwChange(userId, binding.pwChangeEdit.text.toString()).enqueue(object : retrofit2.Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        println("여기 ${response.body().toString()}")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("여기 오류 ${t}")
                    }

                })
                login()
            }
            else if(binding.pwChangeEdit.text.toString() == "" ||
                binding.pwCheckEdit.text.toString() == ""){
                makeText("모두 입력해주세요")
            }
            else{
                makeText("비밀번호가 일치하지 않습니다.")
            }
        }
        binding.editPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                password = s.toString()
                view_pw()
            }

        })
    }
    fun login(){
        startActivity(Intent(this, Login::class.java))
        finish()
    }
    fun view_pw(){
        var cnt = 0;
        for(i in password) {
            if (cnt == 0) {
                binding.firstPw.text = i + ""
                cnt++
            } else if (cnt == 1) {
                binding.secondPw.text = i + ""
                cnt++
            } else if (cnt == 2) {
                binding.thirdPw.text = i + ""
                cnt++
            } else{
                binding.forthPw.text = i + ""
                cnt++
            }
        }
        for(i in 0..(4-cnt)){
            if(i == 1){
                binding.forthPw.text = ""
            }
            else if(i == 2){
                binding.thirdPw.text = ""
            }
            else if(i == 3){
                binding.secondPw.text = ""
            }
            else if(i == 4){
                binding.firstPw.text = ""
            }
        }
    }
    fun next(){
        binding.inputIdLayout.visibility = View.GONE
        binding.pushEmailLayout.visibility = View.VISIBLE
    }
    fun makeText(massage: String){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show()
    }
}