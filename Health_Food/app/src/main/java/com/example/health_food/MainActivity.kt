package com.example.health_food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.health_food.Fragment.Diary
import com.example.health_food.Fragment.Eat
import com.example.health_food.Fragment.MainFragment
import com.example.health_food.Fragment.Search
import com.example.health_food.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val mbinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mbinding.root)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        replaceFrgment(MainFragment())

        mbinding.navigation.itemIconTintList = null
        mbinding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> {
                    replaceFrgment(MainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.diary -> {
                    replaceFrgment(Diary())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    replaceFrgment(Search())
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
}