package com.example.health_food

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class Splash : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Enables Always-on
        setAmbientEnabled()
    }
}