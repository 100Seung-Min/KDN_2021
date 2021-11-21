package com.example.health_food.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.health_food.Adapter.RecyclerViewAdapter3
import com.example.health_food.AddCommunityImage
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

        itemlist.add(CommunityDTO("hello", "hello", "hello", "hello", "hello"))
        itemlist.add(CommunityDTO("hello", "hello", "hello", "hello", "hello"))

        val adapter = RecyclerViewAdapter3(itemlist)

        binding.communityRecyclerview.adapter = adapter
        binding.communityRecyclerview.layoutManager = LinearLayoutManager(context)

        binding.addCommunityBtn.setOnClickListener {
            val intent = Intent(context, AddCommunityImage::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }

        return binding.root
    }
}