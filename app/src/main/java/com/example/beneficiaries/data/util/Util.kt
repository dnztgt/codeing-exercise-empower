package com.example.beneficiaries.data.util

import android.content.Context
import java.io.IOException

fun getJsonFromAssets(context: Context, fileName: String): String? {
    return try {
        val inputStream = context.assets.open(fileName)

        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        String(buffer)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
