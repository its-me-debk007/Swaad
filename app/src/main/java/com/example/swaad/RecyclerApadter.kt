package com.example.swaad

import android.content.Context
import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.swaad.ApiRequest.DataClassRestaurantsItem

//import com.example.swaad.ForgotPassword2.Companion.tokenValue

class RecyclerAdapter(val context: Context,val restaurantData: List<DataClassRestaurantsItem> ) : Adapter<RecyclerAdapter.ViewHolder> ()
{
    companion object
    {
        var id : Int = 1
    }
    private var images = intArrayOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)
//    private var arrayrestaurantname= arrayOf("ResraurantNames","ResraurantNames","ResraurantNames","ResraurantNames","ResraurantNames")
//    private var arrayRating  = arrayOf("ratings","ratings","ratings","ratings","ratings")
    private var arrayTimeDuration= arrayOf("timeduration","timeduration")
    private var arrayPrices = arrayOf("$$$","$$$")
//    private var arrayAddress = arrayOf("Adress","Adress","Adress","Adress","Adress")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view_home,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemImage.setImageResource(images[position])
       holder.itemRestaurantName.text=restaurantData[position].rest_name
        holder.Ratings.text= restaurantData[position].avg_rating.toString()
        holder.timeDuration.text=arrayTimeDuration[position]
        holder.Prices.text=arrayPrices[position]
        holder.RestaurantAdress.text=restaurantData[position].address
        holder.Add.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val activity=v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,Restaurant_page()).addToBackStack(null).commit()
                val position = holder.adapterPosition
                 id =restaurantData[position].id
            }
        })


    }

    override fun getItemCount(): Int {
        return images.size
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
