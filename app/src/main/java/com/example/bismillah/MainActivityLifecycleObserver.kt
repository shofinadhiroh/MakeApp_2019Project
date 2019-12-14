package com.example.bismillah


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.otaliastudios.cameraview.CameraView

/**
 * Observe the lifcycle events of MainActivity and call appropriate camera view APIs
 *
 * Created by Qichuan on 21/6/18.
 */
class MainActivityLifecycleObserver(private val cameraView: CameraView) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startCamera() {
        cameraView.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseCamera() {
        cameraView.stop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyCamera() {
        cameraView.destroy()
    }
}