package com.example.swaad

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.location.LocationManagerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.lang.Exception
import java.util.jar.Manifest
import android.R
import android.R.attr.spacing

import GridSpacingItemDecoration
import android.R.attr
import android.widget.EditText


class home_page : Fragment() {

    //
//    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//    lateinit var locationRequest: LocationRequest
////  the permission id is just an int that must be unique so you can use any number
//    val permission_id=100
    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    //    private lateinit var locationManager:LocationManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(com.example.swaad.R.layout.fragment_home_page, container, false)
        val searchbox = v.findViewById<TextView>(com.example.swaad.R.id.searchView)
        searchbox.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(com.example.swaad.R.id.fragment_container, search_page())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
//        val add = v.findViewById<RecyclerView>(com.example.swaad.R.id.Add)
//        add.setOnClickListener {
//            val fragmentManager =activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(com.example.swaad.R.id.fragment_container,Restaurant_page())
//            fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
//        }
        //Runtimepermissions
//        if (container != null) {
//            if (ContextCompat.checkSelfPermission(
//                    container.context,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(MainActivity(), new String []
//                {
//                    android.Manifest.permission.ACCESS_FINE_LOCATION
//                }, 100
//                );
//            }
//            val location_text = v.findViewById<TextView>(R.id.LocationText)
//            location_text.text = getlocation()
//        }
        layoutManager = GridLayoutManager(container?.context, 2)
        val recyclerView = v.findViewById<RecyclerView>(com.example.swaad.R.id.RecyclerView)
        var spanCount=2
        var spacing = 30
        var includeEdge=false
        recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        //fun that will allow to get last location
//        fun getLastLocation()
//        {
//            //checking permission
//            if(CheckPermission()){
//                //now we check if the location is enabled
//                if(isLocationEnabled())
//                {
//                    fusedLocationProviderClient.LastLocation.addOnCompleteListener { task ->
//                        var location: Location? = task.result
//                        if (location == null) {
//
//                        } else
//                        {
//                            val location_text=v.findViewById<TextView>(R.id.LocationText)
//                            location_text.text=""
//                        }
//
//                    }
//                }
//                else
//                {
//                    Toast.makeText(this,"Please enable your Location",Toast.LENGTH_SHORT).show()     }
//            }
//        }

        return v
    }
}
//LocationManager class provides the facility to get latitude and longitude
//    private fun getlocation(): CharSequence? {
//        try {
//            locationManager = (LocationManager) getApplicationContext ().getSystemService(LOCATION_SERVICE)
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5)
//        }
//        catch ( e:Exception)
//        {
//            e.printStackTrace()
//        }
//
//
//    }
//
//    override fun onLocationChanged(location: Location) {
//
//
//    }

//}
    //        var fusedLocationProviderClient : FusedLocationProviderClient
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient()


//        fun to check the user permission
//    fun CheckPermission():Boolean
//    {
//        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED ||
//            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
//        {
//            return true
//        }
//        return false
//    }
//    //        Now we need to take create a function that will allow us to get user permission
//    fun RequestPermission()
//    {
//        ActivityCompat.requestPermissions(
//            this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),permission_id
//        )
//    }
//    //      a function that check if the location service of the device  is enabled
//    fun isLocationEnabled():Boolean
//    {
//        var locationManager:LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        // it is a bulit in function that checks the permission result
//        // you will use it just for debugging the code
//        if(requestCode==permission_id)
//        {
//            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//              error("You have the permission")
//            }
//        }
//    }
//
//}