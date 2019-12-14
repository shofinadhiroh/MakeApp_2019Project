package com.example.bismillah

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import com.otaliastudios.cameraview.AspectRatio
import com.otaliastudios.cameraview.CameraUtils

import java.lang.ref.WeakReference


/*


    Second Page



*/

class PicturePreviewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_preview)
        val imageView = findViewById<ImageView>(R.id.image)

        val delay = intent.getLongExtra("delay", 0)
        val nativeWidth = intent.getIntExtra("nativeWidth", 0)
        val nativeHeight = intent.getIntExtra("nativeHeight", 0)
        val b = if (image == null) null else image!!.get()
        if (b == null) {
            finish()
            return
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
