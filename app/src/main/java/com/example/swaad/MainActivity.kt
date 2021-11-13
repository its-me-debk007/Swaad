package com.example.swaad

import android.widget.Toast

import android.content.pm.PackageManager
import android.location.Address
import android.location.Location

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import android.location.Geocoder
import com.example.swaad.SplashScreen.splash_screen
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    companion object {
        var latitude: Double = 2.3434.toDouble()
        var longitude: Double = 0.toDouble()
        lateinit var adress: String

    }

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.swaad.R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.swaad.R.id.fragment_container, splash_screen())
        fragmentTransaction.commit()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()
//        adress = getCompleteAddressString(latitude, longitude).toString()
    }

    fun fetchLocation() {
        val task: Task<Location> = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
        }
        task.addOnSuccessListener {
            if (it != null) {
                latitude = it.latitude.toDouble()
                longitude = it.longitude
            } else {
                Toast.makeText(applicationContext, "null", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun getCompleteAddressString(LATITUDE: Double, LONGITUDE: Double): String? {
        var strAdd = ""
        val geocoder = Geocoder(this, Locale.getDefault())
        var adressList=ArrayList<Address>()
        adressList=geocoder.getFromLocation(LATITUDE,LONGITUDE,1) as ArrayList<Address>
        if(adressList==null)
        {
            return "NOTHING FOUND"
        }
        var realAdress=adressList.get(0).getAddressLine(0)
        return realAdress
    }
}

