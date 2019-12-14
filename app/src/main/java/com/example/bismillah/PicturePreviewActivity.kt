package com.example.bismillah

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle

import com.otaliastudios.cameraview.AspectRatio
import com.otaliastudios.cameraview.CameraUtils
import com.theartofdev.edmodo.cropper.CropImage

import java.lang.ref.WeakReference
import android.R.drawable
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.content.ContextWrapper
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.*
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class PicturePreviewActivity : Activity() {

    private val bottomSheetButton by lazy { findViewById<RelativeLayout>(R.id.relative_layout_overlay_activity_take_picture)!! }

    //val imageView = findViewById<ImageView>(R.id.image)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_preview)
        val imageView = findViewById<ImageView>(R.id.image)

        val appName = getString(R.string.app_name)
        val directory = Environment.getExternalStorageDirectory().path + "/" + appName


        val delay = intent.getLongExtra("delay", 0)
        val nativeWidth = intent.getIntExtra("nativeWidth", 0)
        val nativeHeight = intent.getIntExtra("nativeHeight", 0)
        val b = if (image == null) null else image!!.get()
        if (b == null) {
            finish()
            return
        }

        bottomSheetButton.setOnClickListener {
            val fileName = "${System.currentTimeMillis()}_image.jpg"
            CameraUtils.decodeBitmap(b, 1000, 1000) {
                val photo = File(directory, fileName)
                val fileOutputStream = FileOutputStream(photo)
                it.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            }

        }

        CameraUtils.decodeBitmap(b, 1000, 1000) { bitmap ->
            imageView.setImageBitmap(bitmap)



            // approxUncompressedSize.setTitle("Approx. uncompressed size");
            // approxUncompressedSize.setMessage(getApproximateFileMegabytes(bitmap) + "MB");

            // ncr and ar might be different when cropOutput is true.
            val nativeRatio = AspectRatio.of(nativeWidth, nativeHeight)

            // AspectRatio finalRatio = AspectRatio.of(bitmap.getWidth(), bitmap.getHeight());
            // actualResolution.setTitle("Actual resolution");
            // actualResolution.setMessage(bitmap.getWidth() + "x" + bitmap.getHeight() + " (" + finalRatio + ")");
        }

    }

    private fun saveImageToInternalStorage(drawableId:Int):Uri{
        // Get the image from drawable resource as drawable object
        val drawable = ContextCompat.getDrawable(applicationContext,drawableId)

        // Get the bitmap from drawable object
        val bitmap = (drawable as BitmapDrawable).bitmap

        // Get the context wrapper instance
        val wrapper = ContextWrapper(applicationContext)

        // Initializing a new file
        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)


        // Create a file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the stream
            stream.flush()

            // Close stream
            stream.close()
        } catch (e: IOException){ // Catch the exception
            e.printStackTrace()
        }

        // Return the saved image uri
        return Uri.parse(file.absolutePath)
    }
    companion object {

        private var image: WeakReference<ByteArray>? = null

        fun setImage(im: ByteArray?) {
            image = if (im != null) WeakReference(im) else null
        }

        private fun getApproximateFileMegabytes(bitmap: Bitmap): Float {
            return (bitmap.rowBytes * bitmap.height / 1024 / 1024).toFloat()
        }
    }

}
