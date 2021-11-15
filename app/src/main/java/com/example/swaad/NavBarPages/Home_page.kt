package com.example.swaad.NavBarPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient

import GridSpacingItemDecoration
import android.widget.ProgressBar
import com.example.swaad.ApiRequests.DataClassRestaurantsItem
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.MainActivity
import com.example.swaad.RecyclerAdapter
import com.example.swaad.search_page
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home_page : Fragment() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(com.example.swaad.R.layout.fragment_home_page, container, false)
        val searchbox = v.findViewById<TextView>(com.example.swaad.R.id.searchView)
        val progressbar=v.findViewById<ProgressBar>(com.example.swaad.R.id.progressBarHomePage)
        val locationtext=v.findViewById<TextView>(com.example.swaad.R.id.LocationText)
        locationtext.text="Latitude = ${MainActivity.latitude} Longitude = ${MainActivity.longitude}"
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(container?.context)
//        fun fetchLocation()
//        {
//            val task: Task<Location> = fusedLocationProviderClient.lastLocation
//            if (container != null) {
//                if(ActivityCompat.checkSelfPermission(container.context,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
//                    &&ActivityCompat.checkSelfPermission(container.context,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
//                {
//                    ActivityCompat.requestPermissions(Activity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
//                }
//
//            }
//            task.addOnSuccessListener {
//                if(it!=null)
//                {
//                    Toast.makeText(activity,"${it.latitude} ${it.longitude}",Toast.LENGTH_SHORT).show()
//
//                }
//            }
//        }
//        fetchLocation()
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

//
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
        RetrofitClient.init().getRestaurantDetails().enqueue(object : Callback<List<DataClassRestaurantsItem>?> {
            override fun onResponse(
                call: Call<List<DataClassRestaurantsItem>?>,
                response: Response<List<DataClassRestaurantsItem>?>
            ) {
                val responseBody=response.body()!!
                if (container != null) {
                    progressbar.visibility=View.INVISIBLE
                    layoutManager = GridLayoutManager(container?.context, 2)
                    val recyclerView = v.findViewById<RecyclerView>(com.example.swaad.R.id.RecyclerView)
                    var spanCount=2
                    var spacing = 30
                    var includeEdge=false
                    recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
                    recyclerView.layoutManager = layoutManager
                    adapter= RecyclerAdapter(container.context,responseBody)
                    (adapter as RecyclerAdapter).notifyDataSetChanged()
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<DataClassRestaurantsItem>?>, t: Throwable) {
                progressbar.visibility=View.INVISIBLE
                Toast.makeText(activity,"RecyclerViewNotLoaded",Toast.LENGTH_LONG).show()
            }
        })
//        layoutManager = GridLayoutManager(container?.context, 2)
//        val recyclerView = v.findViewById<RecyclerView>(com.example.swaad.R.id.RecyclerView)
//        var spanCount=2
//        var spacing = 30
//        var includeEdge=false
//        recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
//        recyclerView.layoutManager = layoutManager
//        adapter = RecyclerAdapter()


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