package com.example.health_food

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.health_food.Fragment.*
import com.example.health_food.databinding.ActivityMainBinding
import com.example.health_food.model.RecommendDTO
import com.example.health_food.model.User
import com.example.health_food.retrofit.RetrofitClient
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity() : AppCompatActivity() {
    val mbinding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    var recommendDTO:ArrayList<RecommendDTO> = ArrayList()

    var mode: Int? = null

    val PREFERENCE = "food_mode"
    var HEALTH_MODE = "health_mode"
    var LOCAL_MODE = "local_mode"

    var local_mode: String? = ""
    var health_mode: String? = ""

    var tabLayout: TabLayout? = null

    var nickname: String? = null

    var id: String? = null

    var taste: Int = 1

    var foodItem: ArrayList<String>? = null
    var url: ArrayList<String>? = null
    var foodid: ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mbinding.root)
        requestPermission()
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        val pref_login = getSharedPreferences("LOGIN", MODE_PRIVATE)
        id = pref_login.getString("userId", "")
        mode = intent.getIntExtra("mode", 0)
        val toolbar = mbinding.sideNav
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_search_24)
        local_mode = intent.getStringExtra("local_mode")
        health_mode = intent.getStringExtra("health_mode")
        nickname = intent.getStringExtra("user_name")
        foodid = intent.getIntegerArrayListExtra("userid")

        if(local_mode == null && health_mode == null){
            local_mode = pref.getString(LOCAL_MODE, "")
            health_mode = pref.getString(HEALTH_MODE, "")
        }

        val editor = pref.edit()
        editor.putString(LOCAL_MODE, local_mode)
        editor.putString(HEALTH_MODE, health_mode)
        editor.commit()

        if(local_mode == "on"){
            taste = 2
        }


        mbinding.navigation.itemIconTintList = null
        if(mode == 1){
            mbinding.sideNav.visibility = View.GONE
            replaceFrgment(Refrigerator(id!!))
        }
        else if(mode == 0){
            callRetrofit(0)
        }
        else if(mode == 3){
            mbinding.sideNav.visibility = View.VISIBLE
            replaceFrgment(Comunity(id!!))
        }
        mbinding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    mbinding.sideNav.visibility = View.VISIBLE
                    mbinding.tabLayout.visibility = View.VISIBLE
                    tabLayout?.visibility = View.VISIBLE
                    callRetrofit(0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.comunity -> {
                    mbinding.sideNav.visibility = View.VISIBLE
                    mbinding.tabLayout.visibility = View.GONE
                    tabLayout?.visibility = View.GONE
                    replaceFrgment(Comunity(id!!))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    mbinding.sideNav.visibility = View.GONE
                    mbinding.tabLayout.visibility = View.GONE
                    tabLayout?.visibility = View.GONE
                    replaceFrgment(Refrigerator(id!!))
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

        mbinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                callRetrofit(position!!.toInt())

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val position = tab?.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val position = tab?.position
            }

        })
        println("여기 ${id}, ${nickname} ")
        if(id != ""){
            RetrofitClient.api.postGetNickName(id!!).enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                    nickname = response.body()?.get(0)?.nickname
                    println("여기 ${nickname}")
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    println("여기오류 ${t}")
                }
            })
        }

        if(nickname != null){
            RetrofitClient.api.postNameChange(pref_login.getString("userId", "")!!, nickname!!).enqueue(object : retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    println("여기 ${response.body()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("여기 오류 ${t}")
                }

            })
        }
        RetrofitClient.api.postIsTasteDiv(pref_login.getString("userId", "")!!, local_mode!!, health_mode!!).enqueue(object : retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("여기 오류 ${t}")
            }

        })
    }

    private fun replaceFrgment(fragment: Fragment){
        if(fragment == UserProfile()){

        }
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_1, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile -> {
                mbinding.sideNav.visibility = View.GONE
                mbinding.tabLayout.visibility = View.GONE
                tabLayout?.visibility = View.GONE
                val fragment = UserProfile()
                val bundle = Bundle()
                bundle.putString("local_mode", local_mode)
                bundle.putString("health_mode", health_mode)
                bundle.putString("userName", nickname)
                fragment.arguments = bundle
                replaceFrgment(fragment)
            }
            android.R.id.home -> {
                mbinding.sideNav.visibility = View.GONE
                mbinding.tabLayout.visibility = View.GONE
                tabLayout?.visibility = View.GONE
                replaceFrgment(Search(id!!))
            }
        }
        return true
    }
    fun callRetrofit(position: Int){
        mbinding.sideNav.visibility = View.VISIBLE
        mbinding.tabLayout.visibility = View.VISIBLE
        when(position){
            0 -> {
                RetrofitClient.api.getVegetable(taste).enqueue(object : Callback<ArrayList<RecommendDTO>>{
                    override fun onResponse(
                        call: Call<ArrayList<RecommendDTO>>,
                        response: Response<ArrayList<RecommendDTO>>
                    ) {
                        recommendDTO.clear()
                        for(i in response.body()!!){
                            recommendDTO.add(i)
                        }
                        replaceFrgment(MainRecommand(recommendDTO))
                    }

                    override fun onFailure(
                        call: Call<ArrayList<RecommendDTO>>,
                        t: Throwable
                    ) {
                        println("여기 오류 ${t}")
                    }
                })
            }
            1 -> {
                RetrofitClient.api.getMeat(taste).enqueue(object : Callback<ArrayList<RecommendDTO>>{
                    override fun onResponse(
                        call: Call<ArrayList<RecommendDTO>>,
                        response: Response<ArrayList<RecommendDTO>>
                    ) {
                        recommendDTO.clear()
                        for(i in response.body()!!){
                            recommendDTO.add(i)
                        }
                        replaceFrgment(MainRecommand(recommendDTO))
                    }

                    override fun onFailure(
                        call: Call<ArrayList<RecommendDTO>>,
                        t: Throwable
                    ) {
                        println("여기 오류 ${t}")
                    }
                })
            }
            2 -> {
                RetrofitClient.api.getDessert(taste).enqueue(object : Callback<ArrayList<RecommendDTO>>{
                    override fun onResponse(
                        call: Call<ArrayList<RecommendDTO>>,
                        response: Response<ArrayList<RecommendDTO>>
                    ) {
                        recommendDTO.clear()
                        for(i in response.body()!!){
                            recommendDTO.add(i)
                        }
                        replaceFrgment(MainRecommand(recommendDTO))
                    }

                    override fun onFailure(
                        call: Call<ArrayList<RecommendDTO>>,
                        t: Throwable
                    ) {
                        println("여기 오류 ${t}")
                    }
                })
            }
            3 -> {
                RetrofitClient.api.getEtc(taste).enqueue(object : Callback<ArrayList<RecommendDTO>>{
                    override fun onResponse(
                        call: Call<ArrayList<RecommendDTO>>,
                        response: Response<ArrayList<RecommendDTO>>
                    ) {
                        recommendDTO.clear()
                        for(i in response.body()!!){
                            recommendDTO.add(i)
                        }
                        replaceFrgment(MainRecommand(recommendDTO))
                    }

                    override fun onFailure(
                        call: Call<ArrayList<RecommendDTO>>,
                        t: Throwable
                    ) {
                        println("여기 오류 ${t}")
                    }
                })
            }
        }
    }

    fun requestPermission(){
        println("호출")
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                var permissions = arrayOf( android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE )
            ActivityCompat.requestPermissions(this, permissions, 100) }
    }
}