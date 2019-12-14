/*
 * Created by YSN Studio on 8/11/18 11:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 8/11/18 11:00 PM
 */

package com.example.bismillah

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var photos: MutableList<File>
    private lateinit var adapterPhotos: AdapterPhotos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, 100)
            }
        }
        floating_action_button_take_picture_activity_main.setOnClickListener {
            startActivity(Intent(this@MainActivity, TakePictureActivity::class.java))
        }
        photos = mutableListOf()
        adapterPhotos = AdapterPhotos(photos = photos)
        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(
            this,
            androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
        )
    }

    override fun onResume() {
        super.onResume()
        val appName = getString(R.string.app_name)
        val directory = File(Environment.getExternalStorageDirectory().path + "/" + appName)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val files = directory.listFiles()
        photos.clear()
        if (files != null) {
            photos.addAll(files)
        }
        adapterPhotos.refresh(photos = photos)
        if (photos.size == 0) {
            Snackbar.make(findViewById(android.R.id.content), "No photos available", Snackbar.LENGTH_LONG)
                    .show()
        }
    }

}
