package dev.mohancm.locationprovider

import android.Manifest;
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var textViewLocation: TextView
    private lateinit var fetchLocation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        textViewLocation = findViewById(R.id.locationData_tv)
        fetchLocation = findViewById(R.id.fetchLocation_bt)

        // Check if the app has permission to access fine location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request permission if it hasn't been granted
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        fetchLocation.setOnClickListener {
            // Request location updates
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }
    }

    override fun onLocationChanged(location: Location) {
        // Handle location changes here
        val latitude = location.latitude
        val longitude = location.longitude

        // Update the TextView with the latitude and longitude values
        val locationInfo = "Latitude: $latitude\nLongitude: $longitude"
        textViewLocation.text = locationInfo
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