package com.example.gnssinspector.permission

import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

class PermissionManager(
    private val activity: AppCompatActivity
) {

    private lateinit var launcher: ActivityResultLauncher<String>

    fun initialize(onResult: (Boolean) -> Unit) {
        launcher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            onResult(granted)
        }
    }

    fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission() {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}