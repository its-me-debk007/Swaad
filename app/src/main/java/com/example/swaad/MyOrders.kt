package com.example.swaad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MyOrders : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v= inflater.inflate(R.layout.my_orders, container, false)
        val recyclerview=v.findViewById<RecyclerView>(R.id.RecyclerViewMyOrders)
        
        return v
    }
}