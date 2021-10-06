package com.example.health_food.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.health_food.R
import com.example.health_food.databinding.FragmentRefrigeratorBinding
import java.util.zip.Inflater

class Refrigerator : Fragment() {
    private var binding: FragmentRefrigeratorBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRefrigeratorBinding.inflate(inflater, container, false)
        binding?.addMaterial?.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.frame_1, Search())
                    ?.commit()
        }
        return binding!!.root
    }
}