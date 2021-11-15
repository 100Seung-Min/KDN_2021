package com.example.health_food

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.health_food.databinding.FragmentLoginBinding
import com.example.health_food.model.Login
import com.example.health_food.retrofit.RetrofitClient
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.math.log

class Login: AppCompatActivity() {

    val binding by lazy { FragmentLoginBinding.inflate(layoutInflater)}
    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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
            val login = 0
            if(binding.emailEdit.text!!.isNullOrEmpty() || binding.pwEdit.text!!.isNullOrEmpty()){
                Toast.makeText(this, "두개 다 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val id = binding.emailEdit.text.toString()
                val pw = binding.pwEdit.text.toString()
                RetrofitClient.api.getPostResult(id, pw).enqueue(object : retrofit2.Callback<Login>{
                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        println("여기")
                        Log.d("여기", response.body().toString())
                    }

                    override fun onFailure(call: Call<Login>, t: Throwable) {
                        println("여기 오류" + t)
                    }

                })
            }
            if(login != 0){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
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
}