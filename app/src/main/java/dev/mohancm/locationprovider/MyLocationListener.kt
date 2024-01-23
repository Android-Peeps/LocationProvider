package dev.mohancm.locationprovider

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class MyLocationListener(private val context: Context, private val locationCallback: LocationCallback) : LocationListener {

    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun startLocationUpdates() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
            } catch (ex: SecurityException) {
                locationCallback.onError("Location permission not granted")
            }
        } else {
            locationCallback.onError("GPS provider not enabled")
        }
    }

    fun stopLocationUpdates() {
        locationManager.removeUpdates(this)
    }

    interface LocationCallback {
        fun onLocationChanged(location: Location)
        fun onError(errorMessage: String)
    }

    override fun onLocationChanged(location: Location) {
        locationCallback.onLocationChanged(location)
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // Handle provider status changes if needed
    }

    override fun onProviderEnabled(provider: String) {
        // Handle provider enable events if needed
    }

    override fun onProviderDisabled(provider: String) {
        // Handle provider disable events if needed
    }
}