package com.scdc.core.io

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

fun readFileFromAssets(context: Context, fileName: String): String {
    return try {
        context.assets.open(fileName).use { inputStream ->
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            String(buffer, Charset.forName("UTF-8"))
        }
    } catch (ex: IOException) {
        ex.printStackTrace()
        ""
    }
}
