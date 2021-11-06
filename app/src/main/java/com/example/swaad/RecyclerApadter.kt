package com.example.swaad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class RecyclerAdapter : Adapter<RecyclerAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view_home,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        var itemImage:ImageView
        var itemRestaurantName:TextView
        var Ratings:TextView
        var timeDuration : TextView
        var Prices : TextView
        var RestaurantAdress:TextView
        var Add:Button
        init {
            itemImage=itemView.findViewById(R.id.RestaurantImage)
            itemRestaurantName=itemView.findViewById(R.id.restaurantName)
            Ratings=itemView.findViewById(R.id.Ratings)
            timeDuration=itemView.findViewById(R.id.timeDuration)
            Prices=itemView.findViewById(R.id.Prices)
            RestaurantAdress=itemView.findViewById(R.id.RestaurantAdress)
            Add=itemView.findViewById(R.id.Add)
        }
    }
}