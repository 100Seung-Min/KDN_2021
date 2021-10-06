package com.example.health_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.health_food.Fragment.*
import com.example.health_food.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val mbinding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    val PREFERENCE = "food_mode"
    var HEALTH_MODE = "health_mode"
    var LOCAL_MODE = "local_mode"

    var local_mode: String? = ""
    var health_mode: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mbinding.root)
        replaceFrgment(MainFragment())
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)

        val toolbar = mbinding.sideNav
        setSupportActionBar(toolbar)

        local_mode = intent.getStringExtra("local_mode")
        health_mode = intent.getStringExtra("health_mode")

        if(local_mode == null && health_mode == null){
            local_mode = pref.getString(LOCAL_MODE, "")
            health_mode = pref.getString(HEALTH_MODE, "")
        }

        val editor = pref.edit()
        editor.putString(LOCAL_MODE, local_mode)
        editor.putString(HEALTH_MODE, health_mode)
        editor.commit()



        mbinding.navigation.itemIconTintList = null
        mbinding.navigation.selectedItemId = R.id.home
        mbinding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    mbinding.sideNav.visibility = View.VISIBLE
                    mbinding.tabLayout.visibility = View.VISIBLE
                    replaceFrgment(MainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.comunity -> {
                    mbinding.sideNav.visibility = View.VISIBLE
                    mbinding.tabLayout.visibility = View.GONE
                    replaceFrgment(Comunity())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    mbinding.sideNav.visibility = View.GONE
                    mbinding.tabLayout.visibility = View.GONE
                    replaceFrgment(Refrigerator())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun replaceFrgment(fragment: Fragment){
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
                val userProfile = UserProfile()
                val bundle = Bundle()
                bundle.putString("local_mode", local_mode)
                bundle.putString("health_mode", health_mode)
                userProfile.arguments = bundle
                replaceFrgment(userProfile)
            }
        }
        return true
    }
}