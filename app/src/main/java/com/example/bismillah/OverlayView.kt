package com.example.bismillah

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark

/**
 * A overlay view that draws thug life glasses and cigarette bitmaps on top of a detected face
 *
 * Created by Qichuan on 21/6/18.
 */
class OverlayView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // The detected face
    var face: FirebaseVisionFace? = null
        set(value) {
            field = value

            // Trigger redraw when a new detected face object is passed in
            postInvalidate()
        }

    // The preview width
    var previewWidth: Int? = null

    // The preview height
    var previewHeight: Int? = null

    private var widthScaleFactor = 1.0f
    private var heightScaleFactor = 1.0f

    // The glasses bitmap
    private val glassesBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cartooneyes)

    // The cigarette bitmap
    private val cigaretteBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cigarette)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Create local variables here so they canot not be changed anywhere else
        val face = face
        val previewWidth = previewWidth
        val previewHeight = previewHeight

        if (face != null && canvas != null && previewWidth != null && previewHeight != null) {

            // Calculate the scale factor
            widthScaleFactor = canvas.width.toFloat() / previewWidth.toFloat()
            heightScaleFactor = canvas.height.toFloat() / previewHeight.toFloat()

            drawGlasses(canvas, face)
        }
    }

    /***
     * Draw glasses on top of eyes
     */
    private fun drawGlasses(canvas: Canvas, face: FirebaseVisionFace) {
        val leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE)
        val rightEye = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_EYE)

        if (leftEye != null && rightEye != null) {
            val eyeDistance = leftEye.position.x - rightEye.position.x
            val delta = (widthScaleFactor * eyeDistance / 2).toInt()
            val glassesRect = Rect(
                    translateX(leftEye.position.x).toInt() - delta,
                    translateY(leftEye.position.y).toInt() - delta,
                    translateX(rightEye.position.x).toInt() + delta,
                    translateY(rightEye.position.y).toInt() + delta)
            canvas.drawBitmap(glassesBitmap, null, glassesRect, null)
        }
    }

    /**
     * Draw cigarette at the left mouth
     */

    /**
     * Adjusts the x coordinate from the preview's coordinate system to the view coordinate system.
     */
    private fun translateX(x: Float): Float {
        return width - scaleX(x)
    }

    /**
     * Adjusts the y coordinate from the preview's coordinate system to the view coordinate system.
     */
    private fun translateY(y: Float): Float {
        return scaleY(y)
    }

    /** Adjusts a vertical value of the supplied value from the preview scale to the view scale. */
    private fun scaleX(x: Float): Float {
        return x * widthScaleFactor
    }


    /** Adjusts a vertical value of the supplied value from the preview scale to the view scale. */
    private fun scaleY(y: Float): Float {
        return y * heightScaleFactor
    }
}