package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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
        recyclerViewHomePage.layoutManager = layoutManager
        adapter = RecyclerAdapterRestaurantPage()
        recyclerViewHomePage.adapter = adapter
        return v
    }

}