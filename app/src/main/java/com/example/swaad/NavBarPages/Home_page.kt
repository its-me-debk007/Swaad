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
import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.swaad.ApiRequests.DataClassRestaurantsItem
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.help_support
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.app.AppCompatActivity
import com.example.swaad.*
import com.example.swaad.ApiRequests.JsonConverter
import com.example.swaad.SearchPage2Files.SearchPage2


class Home_page : Fragment() {
    companion object{
        var status : String=""
        lateinit var responseData:Response<List<CategoryFoodItem>?>
    }
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var responseBody:List<DataClassRestaurantsItem>
    lateinit var recyclerView:RecyclerView
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
        val sweets=v.findViewById<ImageView>(R.id.Sweets)
        val pizza=v.findViewById<ImageView>(R.id.Pizza)
        val chinese=v.findViewById<ImageView>(R.id.Chinese)
        val burger=v.findViewById<ImageView>(R.id.Burger)

        val jsonConverterCategorySweets=JsonConverterCategory("Sweets")
        val jsonConverterCategoryPizza=JsonConverterCategory("Pizza")
        val jsonConverterCategoryChinese=JsonConverterCategory("Chinese")
        val jsonConverterCategoryBurger=JsonConverterCategory("Burger")
//        val jsonConverterCategoryPizza=JsonConverterCategory("Pizza")

        pizza.setOnClickListener {
            RetrofitClient.init().categoryDish(jsonConverterCategoryPizza).enqueue(object : Callback<List<CategoryFoodItem>?> {
                override fun onResponse(
                    call: Call<List<CategoryFoodItem>?>,
                    response: Response<List<CategoryFoodItem>?>
                ) {
                    status = "Kunal"
                    responseData=response
                    val fragmentManager = activity?.supportFragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_container,SearchPage2())
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.commit()
                }

                override fun onFailure(call: Call<List<CategoryFoodItem>?>, t: Throwable) {

                }
            })
        }
        burger.setOnClickListener {
            RetrofitClient.init().categoryDish(jsonConverterCategoryBurger).enqueue(object : Callback<List<CategoryFoodItem>?> {
                override fun onResponse(
                    call: Call<List<CategoryFoodItem>?>,
                    response: Response<List<CategoryFoodItem>?>
                ) {
                    status = "Kunal"
                    responseData=response
                    val fragmentManager = activity?.supportFragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_container,SearchPage2())
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.commit()
                }

                override fun onFailure(call: Call<List<CategoryFoodItem>?>, t: Throwable) {

                }
            })
        }
        sweets.setOnClickListener {
            RetrofitClient.init().categoryDish(jsonConverterCategorySweets).enqueue(object : Callback<List<CategoryFoodItem>?> {
                override fun onResponse(
                    call: Call<List<CategoryFoodItem>?>,
                    response: Response<List<CategoryFoodItem>?>
                ) {
                    status = "Kunal"
                    responseData=response
                    val fragmentManager = activity?.supportFragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_container,SearchPage2())
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.commit()
                }

                override fun onFailure(call: Call<List<CategoryFoodItem>?>, t: Throwable) {

                }
            })
        }
        chinese.setOnClickListener {
            RetrofitClient.init().categoryDish(jsonConverterCategoryChinese).enqueue(object : Callback<List<CategoryFoodItem>?> {
                override fun onResponse(
                    call: Call<List<CategoryFoodItem>?>,
                    response: Response<List<CategoryFoodItem>?>
                ) {
                    status = "Kunal"
                    responseData=response
                    val fragmentManager = activity?.supportFragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_container,SearchPage2())
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.commit()
                }

                override fun onFailure(call: Call<List<CategoryFoodItem>?>, t: Throwable) {

                }
            })
        }
        locationtext.text="Latitude = ${MainActivity.latitude} Longitude = ${MainActivity.longitude}"
            var location=v.findViewById<ImageView>(R.id.Location)
        location.setOnClickListener {
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(com.example.swaad.R.id.fragment_container,google_maps())
//            fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
            val intent = Intent(activity,google_maps::class.java)
            startActivity(intent)
        }
        searchbox.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(com.example.swaad.R.id.fragment_container, search_page())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        RetrofitClient.init().getRestaurantDetails().enqueue(object : Callback<List<DataClassRestaurantsItem>?> {
            override fun onResponse(
                call: Call<List<DataClassRestaurantsItem>?>,
                response: Response<List<DataClassRestaurantsItem>?>
            ) {
                 responseBody=response.body()!!
                if (container != null) {
                    progressbar.visibility=View.INVISIBLE
                    layoutManager = GridLayoutManager(container?.context, 2)
                    recyclerView = v.findViewById<RecyclerView>(com.example.swaad.R.id.RecyclerView)
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
                Toast.makeText(activity,"Please Reopen Your App",Toast.LENGTH_LONG).show()
            }
        })

        return v
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if(newConfig.orientation== Configuration.ORIENTATION_LANDSCAPE){
            layoutManager = GridLayoutManager(context, 3)

            var spanCount=3
            var spacing = 30
            var includeEdge=false
            recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            recyclerView.layoutManager = layoutManager
            adapter= context?.let { RecyclerAdapter(it,responseBody) }
            (adapter as RecyclerAdapter).notifyDataSetChanged()
            recyclerView.adapter = adapter
        }
        else if(newConfig.orientation== Configuration.ORIENTATION_PORTRAIT){
            layoutManager = GridLayoutManager(context, 2)

            var spanCount=2
            var spacing = 30
            var includeEdge=false
            recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            recyclerView.layoutManager = layoutManager
            adapter= context?.let { RecyclerAdapter(it,responseBody) }
            (adapter as RecyclerAdapter).notifyDataSetChanged()
            recyclerView.adapter = adapter
        }
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