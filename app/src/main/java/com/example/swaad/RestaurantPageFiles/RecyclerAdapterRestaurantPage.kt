package com.example.swaad.RestaurantPageFiles

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.swaad.ApiRequests.DataClass
import com.example.swaad.ApiRequests.DataClassAddedToCart
import com.example.swaad.ApiRequests.RestaurantDishesItem
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.R
import com.example.swaad.SearchPage2Files.RecyclerAdapterSearchPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.FieldPosition

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
//    var pos1: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view_restaurant_page,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var Url=dishData[holder.getAdapterPosition()].photo
        holder.dishImage.load(Url)
        holder.dishCost.text= "₹" + dishData[holder.getAdapterPosition()].price.toString() + ".00"
        holder.dishName.text=dishData[holder.getAdapterPosition()].title
        var flag = 0
        var pos1: Int = 0
        for (i in 0 until dishIdList.size) {
            if (dishIdList[i] == dishData[holder.getAdapterPosition()].id) {
                flag = 1
                pos1 = i
                break
            }
        }
        if (flag == 0) {
            holder.addToCart.text = "ADD"
        }
        else{
            holder.addToCart.text = "ADDED: ${dishCount[pos1]}"
        }
        holder.addToCart.setOnClickListener {
                var flag = 0
                for (i in 0 until dishIdList.size) {
                    if (dishIdList[i] == dishData[holder.getAdapterPosition()].id) {
                        flag = 1
                        pos = i
                        break
                    }
                }
                if (flag == 0) {

                    RetrofitClient.init().addToCart(dishData[holder.getAdapterPosition()].id).enqueue(object :
                        Callback<DataClassAddedToCart?> {
                        override fun onResponse(
                            call: Call<DataClassAddedToCart?>,
                            response: Response<DataClassAddedToCart?>
                        ) {
                            if (response.code() == 200) {
                                cartList.add(holder.dishName.text.toString())
                                dishCostList.add(dishData[holder.getAdapterPosition()].price)
                                basePriceList.add(dishData[holder.getAdapterPosition()].price)
                                dishCount.add(1)
                                dishIdList.add(dishData[holder.getAdapterPosition()].id)
                                restIdList.add(dishData[holder.getAdapterPosition()].restaurant_id)
                                holder.addToCart.text = "ADDED: 1"
                            }
                            else if(response.code() == 400){
                                holder.addToCart.text = "Dish from another restaurant cannot be added."
                            }
                        }
                        override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                            Log.d("Error", "Retrofit is OnFailure")
                        }
                    })

                } else {

                    RetrofitClient.init().addToCart(dishData[holder.getAdapterPosition()].id).enqueue(object :
                        Callback<DataClassAddedToCart?> {
                        override fun onResponse(
                            call: Call<DataClassAddedToCart?>,
                            response: Response<DataClassAddedToCart?>
                        ) {
                            if (response.code() == 200) {
                                dishCount[pos]++
                                dishCostList[pos] = dishCount[pos] * basePriceList[pos]
                                holder.addToCart.text = "ADDED: ${dishCount[pos]}"
                            }
                            else if(response.code() == 400){
                                holder.addToCart.text = "Dish from another restaurant cannot be added."
                            }
                        }
                        override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                            Log.d("Error", "Retrofit is OnFailure")
                        }
                    })
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

