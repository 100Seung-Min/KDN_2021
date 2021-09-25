package com.example.health_food

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.health_food.Fragment.ChoseFood
import com.example.health_food.Fragment.Explain
import com.example.health_food.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    val mbinding by lazy { ActivityStartBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mbinding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        mbinding?.startView?.adapter = adapter
        mbinding?.skip?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    inner class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> {
                    Explain()

                }
                1-> {
                    ChoseFood()
                }
                else -> {
                    Fragment()
                }
            }
        }
    }
}