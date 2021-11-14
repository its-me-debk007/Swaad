package com.example.swaad.RestaurantPageFiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.swaad.ApiRequests.RestaurantDishesItem
import com.example.swaad.R

class RecyclerAdapterRestaurantPage(val context:Context, val dishData : List<RestaurantDishesItem>):Adapter<RecyclerAdapterRestaurantPage.ViewHolder>(){
    companion object{
        var dishNameList = mutableListOf<String>("Chole Bhature", "Samosa")
        var dishCostList = mutableListOf<String>("₹72", "₹106")
    }

//    private var arraydishImage = intArrayOf(R.drawable.home_page_burger,R.drawable.home_page_burger,R.drawable.home_page_burger)
//    private var  arraydishName= arrayOf("Tandoori Chicken","Tandoori Chicken","Tandoori Chicken","Tandoori Chicken")
//    private var arraydishCost= arrayOf("$100","$100","$100","$100")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view_restaurant_page,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var Url=dishData[position].photo
        holder.dishImage.load(Url)

        holder.dishCost.text=dishData[position].price.toString()
        holder.dishName.text=dishData[position].title
        holder.addToCart.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                dishNameList.add(holder.dishName.text as String)
                dishCostList.add(holder.dishCost.text as String)
            }
        })


    }

    override fun getItemCount(): Int {
        return dishData.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var dishName:TextView
        var dishImage:ImageView
        var dishCost:TextView
        var addToCart: Button
        init{
            dishName = itemView.findViewById(R.id.DIshName)
            dishImage = itemView.findViewById(R.id.DishPhoto)
            dishCost = itemView.findViewById(R.id.Money)
            addToCart = itemView.findViewById(R.id.button)
        }
    }
}

