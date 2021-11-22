package com.example.health_food.ImageChange

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

object ImageChange {
    public fun bitmapToString(bitmap: Bitmap): String {

        val byteArrayOutputStream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)

        val byteArray = byteArrayOutputStream.toByteArray()

        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    public fun stringToBitmap(encodedString: String): Bitmap {

        val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
    }
}