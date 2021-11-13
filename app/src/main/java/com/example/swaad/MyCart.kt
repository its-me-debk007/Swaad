package com.example.swaad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.LayoutPages.ReferenceSignUp

class MyCart: Fragment() {
//    private var layoutManager: RecyclerView.LayoutManager?=null
//    private var adapter: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.cart, container, false)

//        layoutManager = LinearLayoutManager(container?.context)
//        val recyclerViewCart = v.findViewById<RecyclerView>(R.id.recyclerViewCart)
//        recyclerViewCart.layoutManager = layoutManager
//        adapter = RecyclerAdapterCart()
//        recyclerViewCart.adapter = adapter

        val itemCount: TextView = v.findViewById(R.id.itemCount)

        v.findViewById<ImageView>(R.id.minus).setOnClickListener{
            if(itemCount.text.toString() != "0"){
                var amount = itemCount.text.toString().toInt()
                amount--
                itemCount.text = amount.toString()
        }
        }
        v.findViewById<ImageView>(R.id.plus).setOnClickListener{
            var amount = itemCount.text.toString().toInt()
            amount++
            itemCount.text = amount.toString()
        }

        v.findViewById<ImageView>(R.id.backBtn).setOnClickListener{
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, home_page())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        return v
    }
}