package com.example.health_food.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.health_food.Adapter.RecyclerViewAdapter3
import com.example.health_food.R
import com.example.health_food.databinding.FragmentComunityBinding
import com.example.health_food.model.CommunityDTO

class Comunity : Fragment() {

    lateinit var binding: FragmentComunityBinding

    var itemlist = ArrayList<CommunityDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentComunityBinding.inflate(inflater, container, false)

        val adapter = RecyclerViewAdapter3(itemlist)

        binding.communityRecyclerview.adapter = adapter
        binding.communityRecyclerview.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}