/*
 * Created by YSN Studio on 8/11/18 11:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 8/11/18 11:00 PM
 */

package com.example.bismillah

import android.app.Activity
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import com.google.android.material.bottomsheet.BottomSheetBehavior
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraUtils
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.Size
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_take_picture.*
import java.io.File
import java.io.FileOutputStream

/*
* Second Page
*
* */


class TakePictureActivity : AppCompatActivity() {

    private var camera: CameraView? = null

    private var mCapturingPicture: Boolean = false
    private var mCapturingVideo: Boolean = false

    // To show stuff in the callback
    private var mCaptureNativeSize: Size? = null
    private var mCaptureTime: Long = 0

    private var controlPanel: ViewGroup? = null

    private val imageView by lazy { findViewById<ImageView>(R.id.camera_view)!! }

    private val bottomSheetButton by lazy { findViewById<Button>(R.id.button2)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_picture)
        val appName = getString(R.string.app_name)
        val directory = Environment.getExternalStorageDirectory().path + "/" + appName
        startFaceProcessor()
        camera_view.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(jpeg: ByteArray?) {
                mCapturingPicture = false
                val callbackTime = System.currentTimeMillis()

                // This can happen if picture was taken with a gesture.
                if (mCaptureTime == 0L) mCaptureTime = callbackTime - 300
                if (mCaptureNativeSize == null) mCaptureNativeSize = camera?.getPictureSize()

                PicturePreviewActivity.Companion.setImage(jpeg)
                val intent = Intent(this@TakePictureActivity, PicturePreviewActivity::class.java)
                intent.putExtra("delay", callbackTime - mCaptureTime)
                intent.putExtra("nativeWidth", mCaptureNativeSize?.getWidth())
                intent.putExtra("nativeHeight", mCaptureNativeSize?.getHeight())
                startActivity(intent)

                mCaptureTime = 0
                mCaptureNativeSize = null
            }
        })
        relative_layout_container_activity_take_picture.setOnClickListener {
            if (mCapturingPicture)
            mCapturingPicture = true
            mCaptureTime = System.currentTimeMillis()
            mCaptureNativeSize = camera_view.getPictureSize()
            camera_view.capturePicture()
        }
        bottomSheetButton.setOnClickListener {
            CropImage.activity().start(this)
        }
    }

    private fun onPicture(jpeg: ByteArray) {
        mCapturingPicture = false
        val callbackTime = System.currentTimeMillis()

        // This can happen if picture was taken with a gesture.
        if (mCaptureTime == 0L) mCaptureTime = callbackTime - 300
        //if (mCaptureNativeSize == null) mCaptureNativeSize = camera.getPictureSize()

        PicturePreviewActivity.Companion.setImage(jpeg)
        val intent = Intent(this@TakePictureActivity, PicturePreviewActivity::class.java)
        intent.putExtra("delay", callbackTime - mCaptureTime)
        //intent.putExtra("nativeWidth", mCaptureNativeSize.getWidth())
        //intent.putExtra("nativeHeight", mCaptureNativeSize.getHeight())
        startActivity(intent)

        mCaptureTime = 0
        mCaptureNativeSize = null
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)

            if (resultCode == Activity.RESULT_OK) {
                val imageUri = result.uri
                analyzeImage(MediaStore.Images.Media.getBitmap(contentResolver, imageUri))
                camera_view.visibility = View.GONE
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "There was some error : ${result.error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun analyzeImage(image: Bitmap?) {
        if (image == null) {
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        imageView.setImageBitmap(null)
        //bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(image)
        val options = FirebaseVisionFaceDetectorOptions.Builder()
            .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
            .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
            .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
            .build()
        val faceDetector = FirebaseVision.getInstance().getVisionFaceDetector(options)
        faceDetector.detectInImage(firebaseVisionImage)
            .addOnSuccessListener {
                val mutableImage = image.copy(Bitmap.Config.ARGB_8888, true)

                detectFaces(it, mutableImage)

                imageView.setImageBitmap(mutableImage)
                //bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            .addOnFailureListener {
                Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            }
    }

    private fun detectFaces(faces: List<FirebaseVisionFace>?, image: Bitmap?) {
        if (faces == null || image == null) {
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        val canvas = Canvas(image)
        val facePaint = Paint()
        facePaint.color = Color.RED
        facePaint.style = Paint.Style.STROKE
        facePaint.strokeWidth = 8F
        val faceTextPaint = Paint()
        faceTextPaint.color = Color.RED
        faceTextPaint.textSize = 40F
        faceTextPaint.typeface = Typeface.DEFAULT_BOLD
        val landmarkPaint = Paint()
        landmarkPaint.color = Color.RED
        landmarkPaint.style = Paint.Style.FILL
        landmarkPaint.strokeWidth = 8F

        for ((index, face) in faces.withIndex()) {

            canvas.drawRect(face.boundingBox, facePaint)
            canvas.drawText("Face$index", (face.boundingBox.centerX() - face.boundingBox.width() / 2) + 8F, (face.boundingBox.centerY() + face.boundingBox.height() / 2) - 8F, faceTextPaint)

            if (face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE) != null) {
                val leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE)!!
                canvas.drawCircle(leftEye.position.x, leftEye.position.y, 8F, landmarkPaint)
            }
            if (face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_EYE) != null) {
                val rightEye = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_EYE)!!
                canvas.drawCircle(rightEye.position.x, rightEye.position.y, 8F, landmarkPaint)
            }
        }
    }

    private fun startFaceProcessor() {
        // Observe activity lifecycle to start, stop and destroy camera view based on lifecycle events
        lifecycle.addObserver(MainActivityLifecycleObserver(camera_view))

        val eye = findViewById<RelativeLayout>(R.id.rellay_face );

        eye.setOnClickListener(){
            val faceProcessor = FaceProcessor(camera_view, overlay_view)
            faceProcessor.startProcessing()
        }



        // Start the face processing
    }

    private fun showProgressDialog() {
        //relative_layout_container_progress_dialog_activity_take_picture.visibility = View.VISIBLE
        //window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                //WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onResume() {
        super.onResume()
        camera_view.start()
    }

    override fun onPause() {
        super.onPause()
        camera_view.stop()
    }
}
