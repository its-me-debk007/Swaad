package com.example.swaad.RestaurantPageFiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.ApiRequests.RestaurantDishesItem
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.NavBarPages.Home_page
import com.example.swaad.R
import com.example.swaad.Rating_Page
import com.example.swaad.RecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Restaurant_page : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterRestaurantPage.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_restaurant_page, container, false)
//        val rating = v.findViewById<Button>(R.id.ratingButton)
//        rating.setOnClickListener {
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.fragment_container, Rating_Page())
//            fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
//
//        }
         val progressBar=v.findViewById<ProgressBar>(R.id.progressBarRestaurantPage)
        layoutManager = LinearLayoutManager(container?.context)
        val back = v.findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, Home_page())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        val recyclerViewHomePage = v.findViewById<RecyclerView>(R.id.recyclerViewRestaurantPage)
        var restaurant_name=v.findViewById<TextView>(R.id.restaurant_name)
        restaurant_name.text= RecyclerAdapter.name
        RetrofitClient.init().getRestaurantDishes(RecyclerAdapter.id).enqueue(object : Callback<List<RestaurantDishesItem>?> {
            override fun onResponse(
                call: Call<List<RestaurantDishesItem>?>,
                response: Response<List<RestaurantDishesItem>?>
            ) {
                val responseBody=response.body()!!
                if (container != null) {
                    progressBar.visibility=View.INVISIBLE
                    recyclerViewHomePage.layoutManager = layoutManager
                    adapter = RecyclerAdapterRestaurantPage(container.context,responseBody)
                    (adapter as RecyclerAdapterRestaurantPage).notifyDataSetChanged()
                    recyclerViewHomePage.adapter = adapter
                }
                else
                {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,"Invalid Id",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<RestaurantDishesItem>?>, t: Throwable) {
                progressBar.visibility=View.INVISIBLE
              Toast.makeText(activity,"Invalid Id",Toast.LENGTH_LONG).show()
            }
        })
//        recyclerViewHomePage.layoutManager = layoutManager
//        adapter = RecyclerAdapterRestaurantPage()
//        recyclerViewHomePage.adapter = adapter
        return v
    }

}