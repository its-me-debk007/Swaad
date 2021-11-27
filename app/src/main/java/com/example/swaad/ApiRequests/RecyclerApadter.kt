package com.example.swaad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.swaad.ApiRequests.DataClassRestaurantsItem
import com.example.swaad.RestaurantPageFiles.Restaurant_page

class RecyclerAdapter(val context: Context,val restaurantData: List<DataClassRestaurantsItem> ) : Adapter<RecyclerAdapter.ViewHolder> ()
{
    companion object
    {
        var id : Int = -1
        var name="Restaurnat_name"
        var flag=0
    }
//    private var images = intArrayOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)
//    private var arrayrestaurantname= arrayOf("ResraurantNames","ResraurantNames","ResraurantNames","ResraurantNames","ResraurantNames")
//    private var arrayRating  = arrayOf("ratings","ratings","ratings","ratings","ratings")
//    private var arrayTimeDuration= arrayOf("timeduration","timeduration")
//    private var arrayPrices = arrayOf("$$$","$$$")
//    private var arrayAddress = arrayOf("Adress","Adress","Adress","Adress","Adress")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view_home,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        var Url=restaurantData[position].image
        holder.itemImage.load(Url)
        holder.itemRestaurantName.text=restaurantData[position].rest_name
        holder.Ratings.text= restaurantData[position].avg_rating.toString()
        holder.timeDuration
        holder.Prices.text=restaurantData[position].expense_rating

        holder.RestaurantAdress.text=restaurantData[position].address
        holder.Add.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val activity=v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    Restaurant_page()
                ).addToBackStack(null).commit()
                val position = holder.adapterPosition
                 id =restaurantData[position].id
                name=restaurantData[position].rest_name
            }
        })
        holder.favourites.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (flag%2==0) {
                    val favourites = v?.findViewById<ImageView>(R.id.favourites)
                    favourites?.setImageResource(R.drawable.ic_home_page_favourates)
                    flag++
                }
            else
                {
                    val favourites = v?.findViewById<ImageView>(R.id.favourites)
                    favourites?.setImageResource(R.drawable.ic_favourates_home)
                    flag++
                }
            }
        })

    }

    override fun getItemCount(): Int {
        return restaurantData.size
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
        var favourites:ImageView
        init {
            itemImage=itemView.findViewById(R.id.RestaurantImage)
            itemRestaurantName=itemView.findViewById(R.id.restaurantName)
            Ratings=itemView.findViewById(R.id.Ratings)
            timeDuration=itemView.findViewById(R.id.timeDuration)
            Prices=itemView.findViewById(R.id.Prices)
            RestaurantAdress=itemView.findViewById(R.id.RestaurantAdress)
            Add=itemView.findViewById(R.id.Add)
            favourites=itemView.findViewById(R.id.favourites)
            }
        }

}
