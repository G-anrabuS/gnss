package com.example.gnssinspector

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gnssinspector.databinding.ActivityMainBinding
import com.example.gnssinspector.permission.PermissionManager
import com.example.gnssinspector.gnss.GnssManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionManager: PermissionManager
    private lateinit var gnssManager: GnssManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupInsets()

        permissionManager = PermissionManager(this)

        gnssManager = GnssManager(this)

        permissionManager.initialize { granted ->

            if (granted) {

                showGnssStatus()

            } else {

                binding.statusText.setText(R.string.permission_denied)

            }

        }

        initializeUI()

        checkPermission()
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                bars.left,
                bars.top,
                bars.right,
                bars.bottom
            )
            insets
        }
    }

    private fun initializeUI() {
        binding.statusText.setText(R.string.application_started)
    }

    private fun checkPermission() {
        if (permissionManager.hasLocationPermission()) {
            binding.statusText.setText(R.string.permission_granted)
        } else {
            permissionManager.requestLocationPermission()
        }
    }

    private fun showGnssStatus() {

        val builder = StringBuilder()

        builder.appendLine("GNSS Hardware : ${gnssManager.hasGnssHardware()}")

        builder.appendLine("GPS Enabled   : ${gnssManager.isGpsEnabled()}")

        binding.statusText.text = builder.toString()
    }
}