package com.example.swaad

import android.app.Activity
import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.SplashScreen.splash_screen
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.material.navigation.NavigationView
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
//        if (null == savedInstanceState) {
//            getSupportFragmentManager().beginTransaction()
//                .addToBackStack("fragmentA")
//                .replace(R.id.container, splash_screen(), "fragmentA")
//                .commit();
//        }
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.swaad.R.id.fragment_container, splash_screen())
        fragmentTransaction.addToBackStack(null)
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
    override fun onBackPressed() {
        val index = supportFragmentManager.getBackStackEntryCount()
//        val fragment = supportFragmentManager.popBackStack()
//        val backEntry = supportFragmentManager.getBackStackEntryAt(index);
//        var tag:String? =backEntry.getName();
        val fragment = supportFragmentManager.findFragmentByTag("fragmentLogin")
////        if(tag!=fragment)

        val fragmentsInStack = supportFragmentManager.backStackEntryCount
        if (fragmentsInStack > 1  ) { // If we have more than one fragment, pop back stack
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(com.example.swaad.R.id.fragment_container, FragmentLogIn())
            fragmentTransaction.commit()
//            supportFragmentManager.popBackStack("fragmentLogin",FragmentManager.POP_BACK_STACK_INCLUSIVE)
            for (i in 0 until index) {
                supportFragmentManager.popBackStack()
            }
//            Toast.makeText(this,"fiest is called",Toast.LENGTH_LONG).show()
    }
        else if (fragmentsInStack<1) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Confirm Exit")
            builder.setMessage("Are you sure you want to exit ?")
                builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->finish()  })
                    builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->dialog.cancel()  })
           builder.show()// Finish activity, if only one fragment left, to prevent leaving empty screen
        }
        else {
            super.onBackPressed()
        }
    }

}


