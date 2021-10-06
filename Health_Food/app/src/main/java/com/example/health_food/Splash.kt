package com.example.health_food

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class Splash : AppCompatActivity() {

    val PREFERENCE = "food_mode"
    var HEALTH_MODE = "health_mode"
    var LOCAL_MODE = "local_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        val local_food = pref.getString(LOCAL_MODE, "")
        val health_food = pref.getString(HEALTH_MODE, "")

        if(local_food == "" && health_food == ""){
            val editor = pref.edit()
            editor.putString(LOCAL_MODE, "on")
            editor.putString(HEALTH_MODE, "off")
            editor.commit()
        }

        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, 2000)
    }
}