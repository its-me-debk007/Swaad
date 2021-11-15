package com.example.swaad

import android.app.Activity
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
import android.view.MenuItem
import android.window.SplashScreen
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.swaad.AuthPages.ForgotPassword2
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.SplashScreen.splash_screen
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList
import android.R
import androidx.fragment.app.FragmentManager


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
//        if (null == savedInstanceState) {
//            getSupportFragmentManager().beginTransaction()
//                .addToBackStack("fragmentLogin")
//                .replace(com.example.swaad.R.id.fragment_container,splash_screen())
//                .commit();
//        }
//        replaceFragment(.newInstance(), "fragmentlogin");
//        adress = getCompleteAddressString(latitude, longitude).toString()
    }

    fun fetchLocation() {
        val task: Task<Location> = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
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
        var adressList = ArrayList<Address>()
        adressList = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1) as ArrayList<Address>
        if (adressList == null) {
            return "NOTHING FOUND"
        }
        var realAdress = adressList.get(0).getAddressLine(0)
        return realAdress
    }
//
//    fun replaceFragment(fragment: Fragment, tag: String?) {
//        //Get current fragment placed in container
//        val currentFragment = supportFragmentManager.findFragmentById(com.example.swaad.R.id.fragment_container)
//
//        //Prevent adding same fragment on top
//        if (currentFragment!!.javaClass == fragment.javaClass) {
//            return
//        }
//
//        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
//        if (supportFragmentManager.findFragmentByTag(tag) != null) {
//            supportFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        }
//
//        //Otherwise, just replace fragment
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack(tag)
//            .replace(com.example.swaad.R.id.container, fragment, tag)
//            .commit()
//    }
//    override fun onBackPressed() {
//        val fragmentsInStack = supportFragmentManager.backStackEntryCount
//        if (fragmentsInStack > 1) { // If we have more than one fragment, pop back stack
//            supportFragmentManager.popBackStack()
//        } else if (fragmentsInStack == 1) { // Finish activity, if only one fragment left, to prevent leaving empty screen
//            finish()
//        } else {
//            super.onBackPressed()
//        }
//    }
}

