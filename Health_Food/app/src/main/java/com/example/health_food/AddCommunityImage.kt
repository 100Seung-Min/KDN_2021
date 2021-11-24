package com.example.health_food

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.health_food.ImageChange.ImageChange
import com.example.health_food.databinding.ActivityAddCommunityImageBinding
import com.example.health_food.model.CommunityDTO
import com.example.health_food.retrofit.RetrofitClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class AddCommunityImage : AppCompatActivity() {

    var currentUri: Uri? = null

    val binding by lazy { ActivityAddCommunityImageBinding.inflate(layoutInflater) }

    var file: File? = null
    var requestFile: RequestBody? = null
    var body: MultipartBody.Part? = null
    var id: String? = null
    var mode: Int = 1
    lateinit var responseCommunityDTO: CommunityDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pref = getSharedPreferences("LOGIN", MODE_PRIVATE)
        val userId = pref.getString("userId", "")
        id = intent.getStringExtra("id")

        if(id != null){
            mode = 2
            RetrofitClient.api.postPageOneList(id!!).enqueue(object : retrofit2.Callback<ArrayList<CommunityDTO>>{
                override fun onResponse(
                    call: Call<ArrayList<CommunityDTO>>,
                    response: Response<ArrayList<CommunityDTO>>
                ) {
                    responseCommunityDTO = response.body()!!.get(0)
                    viewImage()
                    binding.detailTxt.setText(responseCommunityDTO.content)
                    binding.hashtagTxt.setText(responseCommunityDTO.tag)
                    file = File(responseCommunityDTO.url)
                }
                override fun onFailure(call: Call<ArrayList<CommunityDTO>>, t: Throwable) {
                    println("여기 오류 ${t}")
                }
            })
        }

        binding.addImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE)
            startActivityForResult(intent, 0)
        }

        binding.uploadBtn.setOnClickListener {
            requestFile = RequestBody.create(MediaType.parse("image/*"), file)
            body = MultipartBody.Part.createFormData("picture", file?.name, requestFile)
            val content = binding.detailTxt.text.toString()
            val tag = binding.hashtagTxt.text.toString()
            val userIdBody = RequestBody.create(MediaType.parse("text/*"), userId)
            val contentBody = RequestBody.create(MediaType.parse("text/*"), content)
            val tagBody = RequestBody.create(MediaType.parse("text/*"),tag)
            val url = file.toString()
            val urlBody = RequestBody.create(MediaType.parse("text/*",), url)
            if(mode == 1){
                RetrofitClient.api.postUpload(userIdBody, body!!, tagBody, contentBody, urlBody).enqueue(object : retrofit2.Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        main_intent()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("여기 오류${t}")
                    }
                })
            }
            else{
                val idBody = RequestBody.create(MediaType.parse("text/*"), id)
                RetrofitClient.api.postPageUpdate(idBody, userIdBody, body!!, tagBody, contentBody, urlBody).enqueue(object : retrofit2.Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        main_intent()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("여기 오류 ${t}")
                    }
                })
            }
        }
    }

    fun main_intent(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("mode", 3)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0)
        {
            if(resultCode == RESULT_OK)
            {
                currentUri = data?.data
                file = File(getPathFromUri(currentUri))

                currentUri?.let {
                    if(Build.VERSION.SDK_INT < 28) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver,
                            currentUri
                        )
                        binding?.addImg.setImageBitmap(bitmap)
                    } else {
                        val source = ImageDecoder.createSource(this.contentResolver, currentUri!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding?.addImg.setImageBitmap(bitmap)
                    }
                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
    fun getPathFromUri(uri: Uri?): String? {
        val cursor: Cursor? = contentResolver.query(uri!!, null, null, null, null)
        cursor?.moveToNext()
        val path: String = cursor!!.getString(cursor!!.getColumnIndex("_data"))
        cursor.close()
        return path
    }

    fun viewImage(){
        Glide.with(this)
            .load("http://10.120.74.60:3000/images/" + responseCommunityDTO.picture)
            .into(binding.addImg)
    }
}