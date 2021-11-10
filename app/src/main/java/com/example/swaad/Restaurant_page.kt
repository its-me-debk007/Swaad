package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.ApiRequest.RestaurantDishesItem
import com.example.swaad.ApiRequest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Restaurant_page : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterRestaurantPage.ViewHolder>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_restaurant_page, container, false)
        layoutManager = LinearLayoutManager(container?.context)
        val back = v.findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, home_page())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        val recyclerViewHomePage = v.findViewById<RecyclerView>(R.id.recyclerViewRestaurantPage)

        RetrofitClient.init().getRestaurantDishes(RecyclerAdapter.id).enqueue(object : Callback<List<RestaurantDishesItem>?> {
            override fun onResponse(
                call: Call<List<RestaurantDishesItem>?>,
                response: Response<List<RestaurantDishesItem>?>
            ) {
                val responseBody=response.body()!!
                if (container != null) {
                    recyclerViewHomePage.layoutManager = layoutManager
                    adapter = RecyclerAdapterRestaurantPage(container.context,responseBody)
                    (adapter as RecyclerAdapterRestaurantPage).notifyDataSetChanged()
                    recyclerViewHomePage.adapter = adapter
                }
                else
                {
                    Toast.makeText(activity,"Invalid Id",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<RestaurantDishesItem>?>, t: Throwable) {
              Toast.makeText(activity,"Invalid Id",Toast.LENGTH_LONG).show()
            }
        })
//        recyclerViewHomePage.layoutManager = layoutManager
//        adapter = RecyclerAdapterRestaurantPage()
//        recyclerViewHomePage.adapter = adapter
        return v
    }

}