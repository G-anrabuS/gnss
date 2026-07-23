package com.example.gnssinspector.gnss

import android.content.Context
import android.location.LocationManager

class GnssManager(context: Context) {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun hasGnssHardware(): Boolean {
        return locationManager.allProviders.contains(LocationManager.GPS_PROVIDER)
    }

    fun isGpsEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}