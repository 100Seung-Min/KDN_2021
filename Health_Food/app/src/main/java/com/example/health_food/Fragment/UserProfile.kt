package com.example.health_food.Fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.health_food.Login
import com.example.health_food.MainActivity
import com.example.health_food.R
import com.example.health_food.databinding.FragmentUserProfileBinding
import com.kakao.sdk.user.UserApiClient

class UserProfile : Fragment() {
    private var binding: FragmentUserProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)

        var local_mode = arguments?.getString("local_mode")
        var health_mode = arguments?.getString("health_mode")
        if(local_mode == "on" && health_mode == "on"){
            binding?.localOn?.setTextColor(Color.parseColor("#00FF29"))
            binding?.healthOn?.setTextColor(Color.parseColor("#00FF29"))
        }
        else if(local_mode == "on"){
            binding?.localOn?.setTextColor(Color.parseColor("#00FF29"))
        }
        else{
            binding?.healthOn?.setTextColor(Color.parseColor("#00FF29"))
        }
        binding?.localChose?.setOnClickListener{
            if(local_mode == "on" && health_mode == "off"){
                Toast.makeText(activity, "두개 다 off할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else if(local_mode == "off"){
                binding?.localOn?.setTextColor(Color.parseColor("#00FF29"))
                local_mode = "on"
            }
            else{
                binding?.localOn?.setTextColor(Color.parseColor("#000000"))
                local_mode = "off"
            }
        }
        binding?.healthChose?.setOnClickListener{
            if(local_mode == "off" && health_mode == "on"){
                Toast.makeText(activity, "두개 다 off할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else if(health_mode == "off"){
                binding?.healthOn?.setTextColor(Color.parseColor("#00FF29"))
                health_mode = "on"
            }
            else{
                binding?.healthOn?.setTextColor(Color.parseColor("#000000"))
                health_mode = "off"
            }
        }

        binding?.saveProfile?.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("local_mode", local_mode)
            intent.putExtra("health_mode", health_mode)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        binding?.logoutBtn?.setOnClickListener {
            UserApiClient.instance.logout {
                val intent = Intent(activity, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
            }
        }
        return binding!!.root
    }
}