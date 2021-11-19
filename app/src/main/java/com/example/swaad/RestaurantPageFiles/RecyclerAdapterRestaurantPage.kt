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
import com.example.swaad.SearchPage2Files.RecyclerAdapterSearchPage

class RecyclerAdapterRestaurantPage(val context:Context, val dishData : List<RestaurantDishesItem>):Adapter<RecyclerAdapterRestaurantPage.ViewHolder>(){
    companion object{
        var cartList = mutableListOf<String>()
        var dishCostList = mutableListOf<Int>()
        var basePriceList = mutableListOf<Int>()
        var dishCount = mutableListOf<Int>()
        var dishIdList = mutableListOf<Int>()
        var restIdList = mutableListOf<Int>()
    }
    var pos:Int = 0
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
        holder.addToCart.setOnClickListener {
            var flag = 0
            for (i in 0 until dishIdList.size) {
                if (dishIdList[i] == dishData[position].id) {
                    flag = 1
                    pos = i
                    break
                }
            }
            if (flag == 0) {
                cartList.add(holder.dishName.text.toString())
                dishCostList.add(dishData[position].price)
                basePriceList.add(dishData[position].price)
                dishCount.add(1)
                dishIdList.add(dishData[position].id)
                restIdList.add(dishData[position].restaurant_id)
            } else {
                dishCount[pos]++
                dishCostList[pos] = dishCount[pos] * basePriceList[pos]
            }
        }

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
            addToCart = itemView.findViewById(R.id.addForCart)
        }
    }
}

