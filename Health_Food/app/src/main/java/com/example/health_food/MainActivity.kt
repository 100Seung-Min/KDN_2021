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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mbinding.root)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        replaceFrgment(MainFragment())
        val toolbar = mbinding.sideNav
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar


        actionbar!!.setDisplayShowCustomEnabled(true)
        actionbar!!.setDisplayShowTitleEnabled(false)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        mbinding.navigation.itemIconTintList = null
        mbinding.navigation.selectedItemId = R.id.home
        mbinding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    mbinding.sideNav.visibility = View.VISIBLE
                    replaceFrgment(MainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.comunity -> {
                    mbinding.sideNav.visibility = View.GONE
                    replaceFrgment(Comunity())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.search -> {
                    mbinding.sideNav.visibility = View.GONE
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.sidemenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.side_health -> {
                Toast.makeText(this, "건강식 선택", Toast.LENGTH_SHORT).show()
            }
            R.id.side_local -> {
                Toast.makeText(this, "일반식 선택", Toast.LENGTH_SHORT).show()
            }
            R.id.side_say -> {
                startActivity(Intent(this, Complain::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}