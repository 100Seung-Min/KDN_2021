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
import com.example.health_food.ImageChange.ImageChange
import com.example.health_food.databinding.ActivityAddCommunityImageBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pref = getSharedPreferences("LOGIN", MODE_PRIVATE)
        val userId = pref.getString("userId", "")

        binding.addImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE)
            startActivityForResult(intent, 0)
        }

        binding.uploadBtn.setOnClickListener {
            requestFile = RequestBody.create(MediaType.parse("image/*"), file)
            body = MultipartBody.Part.createFormData("picture", file?.name, requestFile)
            println("여기 ${file} ${requestFile} ${body}")
            val content = binding.detailTxt.text.toString()
            val tag = binding.hashtagTxt.text.toString()
            val userIdBody = RequestBody.create(MediaType.parse("text/*"), userId)
            val contentBody = RequestBody.create(MediaType.parse("text/*"), content)
            val tagBody = RequestBody.create(MediaType.parse("text/*"),tag)
            println("여기 ${userIdBody} ${tagBody} ${contentBody}")
            RetrofitClient.api.postUpload(userIdBody, body!!, tagBody, contentBody).enqueue(object : retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    println("여기 ${response.body()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("여기 오류${t}")
                }
            })
        }
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
                            this.contentResolver,
                            currentUri
                        )
                        binding.addImg.setImageBitmap(bitmap)
                    } else {
                        val source = ImageDecoder.createSource(this.contentResolver, currentUri!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding.addImg.setImageBitmap(bitmap)
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
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
}