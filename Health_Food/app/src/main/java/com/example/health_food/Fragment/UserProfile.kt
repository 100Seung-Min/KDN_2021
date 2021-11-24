package com.example.health_food.Fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.health_food.Login
import com.example.health_food.MainActivity
import com.example.health_food.R
import com.example.health_food.databinding.FragmentUserProfileBinding
import com.kakao.sdk.user.UserApiClient
import java.io.File

class UserProfile : Fragment() {
    private var binding: FragmentUserProfileBinding? = null

    var currentUri: Uri? = null
    var file: File? = null

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
        var username = arguments?.getString("userName")
        binding?.userNameTxt?.text = username
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
            intent.putExtra("user_name", binding?.userNameTxt?.text.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        binding?.logoutBtn?.setOnClickListener {
            UserApiClient.instance.logout {
                val intent = Intent(activity, Login::class.java)
                intent.putExtra("login", "logout")
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
            }
        }


        binding?.userNameTxt?.setOnClickListener {
            binding?.userNameTxt?.visibility = View.GONE
            binding?.userNameEditTxt?.setText(binding?.userNameTxt!!.text.toString())
            binding?.userNameEditTxt?.visibility = View.VISIBLE
        }


        binding?.userNameEditTxt?.setOnClickListener {
            binding?.userNameTxt?.visibility = View.VISIBLE
            binding?.userNameTxt?.setText(binding?.userNameEditTxt!!.text.toString())
            binding?.userNameEditTxt?.visibility = View.GONE
        }

        binding?.userProfileImg?.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent, 0)
        }


        return binding!!.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                currentUri = data?.data
                file = File(getPathFromUri(currentUri))
                currentUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            context?.contentResolver,
                            currentUri
                        )
                        binding?.userProfileImg?.setImageBitmap(bitmap)
                    } else {
                        val source = ImageDecoder.createSource(context?.contentResolver!!, currentUri!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding?.userProfileImg?.setImageBitmap(bitmap)
                    }
                }
            } else if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                Toast.makeText(context, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
    fun getPathFromUri(uri: Uri?): String? {
        val cursor: Cursor? = context?.contentResolver?.query(uri!!, null, null, null, null)
        cursor?.moveToNext()
        val path: String = cursor!!.getString(cursor!!.getColumnIndex("_data"))
        cursor.close()
        return path
    }
}