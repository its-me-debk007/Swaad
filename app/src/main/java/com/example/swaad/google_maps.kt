package com.example.swaad

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

class google_maps : AppCompatActivity(),OnMapReadyCallback{
    private var  mMap: GoogleMap?=null
    lateinit var mapView: MapView
    private val MAP_VIEW_BUNDLE_KEY="AIzaSyDVJScQHxVyc0eL6QYMUeDNaJT2xwMXBig"
    override fun onMapReady(googleMap: GoogleMap) {
        mapView.onResume()
        mMap=googleMap
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
        )
        {
            return
        }
        mMap!!.setMyLocationEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_google_maps)
        askGalleryPermissionLocation()
        mapView=findViewById(R.id.map2)
        var mapViewBundle:Bundle?=null
        Toast.makeText(this,mapViewBundle.toString(),Toast.LENGTH_LONG).show()
        if(savedInstanceState != null)
        {
            mapViewBundle=savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(google_maps())
    }
    public override fun onSaveInstanceState(
        outState: Bundle,
        outPersistentState: PersistableBundle
    ) {
        askGalleryPermissionLocation()
        var mapViewBundle=outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if(mapViewBundle==null)
        {
            mapViewBundle= Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY,mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }
    private fun askGalleryPermissionLocation()
    {
        askPermission(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        {

        }.onDeclined{ e->
            if(e.hasDenied())
            {
                e.denied.forEach()
                {
                }
                AlertDialog.Builder(this)
                    .setMessage("Accept Permission")
                    .setPositiveButton("yes"){ _,_->
                        e.askAgain()
                    }
                    .setNegativeButton("no")
                    {
                            dialog, which ->dialog.cancel()
                    }
                    .show()
            }
        }
    }

}