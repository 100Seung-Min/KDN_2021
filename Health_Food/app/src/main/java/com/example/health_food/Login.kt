package com.example.health_food

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health_food.databinding.FragmentLoginBinding
import com.example.health_food.retrofit.RetrofitClient
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Response
import java.util.*

class Login: AppCompatActivity() {

    val binding by lazy { FragmentLoginBinding.inflate(layoutInflater)}
    var callbackManager: CallbackManager? = null
    var login: String? = ""
    var islogout: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val pref = getSharedPreferences("LOGIN", MODE_PRIVATE)
        val edit = pref.edit()
        islogout = intent.getStringExtra("login")
        if(islogout == "logout"){
            edit.putString("login", "logout")
            edit.commit()
        }
        login = pref.getString("login", " ")
        if(login == "login"){
            login()
        }
        callbackManager = CallbackManager.Factory.create()
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (token != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        binding.kakaoLoginBtn.setOnClickListener {
            if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
        binding.loginBtn.setOnClickListener {
            if(binding.emailEdit.text!!.isNullOrEmpty() || binding.pwEdit.text!!.isNullOrEmpty()){
                Toast.makeText(this, "두개 다 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val id = binding.emailEdit.text.toString()
                val pw = binding.pwEdit.text.toString()
                RetrofitClient.api.postLogin(id, pw).enqueue(object : retrofit2.Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.body().toString().equals("hi")){
                            edit.putString("login", "login")
                            edit.commit()
                            login()
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("여기 오류" + t)
                    }

                })
            }
        }
        binding.signUp.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
        binding.findPassword.setOnClickListener {
            startActivity(Intent(this, Findpassword::class.java))
            finish()
        }
        binding.facebookLoginBtn.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
            }
            else if (tokenInfo != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
    fun login(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}