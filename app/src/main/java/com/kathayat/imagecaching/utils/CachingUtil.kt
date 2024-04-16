package com.kathayat.imagecaching.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileOutputStream
import java.net.URL

class CachingUtil {

    companion object {

        // saving the image in internal storage
        fun storeBitmap(bitmap: Bitmap, context: Context) {
            val fileOutputStream: FileOutputStream =
                context.openFileOutput(System.currentTimeMillis().toString(), Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        }

        // loading the cached image from internal storage
        fun loadBitmap(context: Context): Bitmap? {
            return BitmapFactory.decodeFile(context.filesDir.absolutePath + "/" + "Constants.TEMP_IMAGE_NAME")
        }

        // download an image with the url
        fun getBitmapFromURL(url:String): Bitmap? {
            val url = URL(url)
            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
        }
    }
}