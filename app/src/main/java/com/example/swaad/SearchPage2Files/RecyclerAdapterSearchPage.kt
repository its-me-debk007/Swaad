package com.example.swaad.SearchPage2Files

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.swaad.*
import com.example.swaad.ApiRequests.DataClassAddedToCart
import com.example.swaad.ApiRequests.DataGetDishesList
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.NavBarPages.Home_page.Companion.AccessToken
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.basePriceList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.cartList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCostList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCount
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishIdList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.restIdList
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerAdapterSearchPage(val context: Context, val restaurantData: List<DataGetDishesList>): RecyclerView.Adapter<RecyclerAdapterSearchPage.ViewHolder>() {

    companion object{
        var pos: Int = 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterSearchPage.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_page2_card_view, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterSearchPage.ViewHolder, position: Int) {
//        holder.restaurantName.text = restaurants[position]
//        holder.dishName.text = restNames[position].rest_name
//        if (status == "Kunal") {
//            val restaurantData: List<CategoryFoodItem> = responseDataKunal
//            dataSize = restaurantData.size
//
//            holder.dishName.text = restaurantData[position].title
////        holder.categoryName.text = categories[position]
//            holder.priceValue.text = prices[position]
////        holder.bestseller.text = bestsellers[position]
////        holder.dishPic.setImageResource(dishPics[position])
//
//            holder.addBtn.setOnClickListener {
//                var flag = 0
//                for (dish in cartList) {
//                    if (dish == holder.dishName.text.toString()) {
//                        flag = 1
//                        break
//                    }
//                }
//                if (flag == 0) {
//                    cartList.add(holder.dishName.text.toString())
//                    dishCostList.add(holder.priceValue.text.toString())
//                    dishCount.add(position, 1)
//                } else
//                    dishCount[position]++
//            }
//
//        } else {
//            val restaurantData: List<DataGetRestaurantNames> = responseDataDebashish
//            dataSize = restaurantData.size
        var imgUrl = restaurantData[position].image
        holder.dishPic.load(imgUrl)
        holder.restaurantName.text = "By " + restaurantData[position].restaurant_name
        holder.dishName.text = restaurantData[position].title
        holder.categoryName.text = "In " + restaurantData[position].category
        holder.priceValue.text = "₹" + restaurantData[position].price.toString() + "0"

        holder.addBtn.setOnClickListener {
//                var flag = 0
//                for (i in 0 until dishIdList.size) {
//                    if (dishIdList[i] == restaurantData[position].id) {
//                        flag = 1
//                        pos = i
//                        break
//                    }
//                }
//                if (flag == 0) {
//                    cartList.add(holder.dishName.text.toString())
//                    dishCostList.add(restaurantData[holder.absoluteAdapterPosition].price)
//                    basePriceList.add(restaurantData[holder.absoluteAdapterPosition].price)
//                    dishCount.add(1)
//                    dishIdList.add(restaurantData[holder.absoluteAdapterPosition].id)
//                    restIdList.add(restaurantData[holder.absoluteAdapterPosition].restaurant_id)
//                    holder.addBtn.text = "ADDED: 1"
//                } else {
//                    dishCount[pos]++
//                    dishCostList[pos] = dishCount[pos] * basePriceList[pos]
//                    holder.addBtn.text = "ADDED: ${dishCount[pos]}"
//                }
//        }
            holder.addBtn.isEnabled = false
            var flag = 0
            for (i in 0 until dishIdList.size) {
                if (dishIdList[i] == restaurantData[position].id) {
                    flag = 1
                    pos = i
                    break
                }
            }
            if (flag == 0) {
                RetrofitClient.init().addToCart("Bearer ${AccessToken}", restaurantData[position].id).enqueue(object :
                    Callback<DataClassAddedToCart?> {
                    override fun onResponse(
                        call: Call<DataClassAddedToCart?>,
                        response: Response<DataClassAddedToCart?>
                    ) {
                        if (response.code() == 201) {
                            cartList.add(holder.dishName.text.toString())
                            dishCostList.add(restaurantData[position].price)
                            basePriceList.add(restaurantData[position].price)
                            dishCount.add(1)
                            dishIdList.add(restaurantData[position].id)
                            restIdList.add(restaurantData[position].restaurant_id)
                            holder.addBtn.text = "ADDED: 1"
                            holder.addBtn.isEnabled = true
                        } else if (response.code() == 400) {
                            holder.addBtn.text = "Dish from another restaurant cannot be added."
                            holder.addBtn.isEnabled = true
                        }
                    }

                    override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                        Log.d("Error", "Retrofit is OnFailure")
                        holder.addBtn.isEnabled = true
                    }
                })

            } else {

                RetrofitClient.init().addToCart("Bearer ${AccessToken}", restaurantData[position].id).enqueue(object :
                    Callback<DataClassAddedToCart?> {
                    override fun onResponse(
                        call: Call<DataClassAddedToCart?>,
                        response: Response<DataClassAddedToCart?>
                    ) {
                        if (response.code() == 200) {
                            dishCount[pos]++
                            dishCostList[pos] = dishCount[pos] * basePriceList[pos]
                            holder.addBtn.text = "ADDED: ${dishCount[pos]}"
                            holder.addBtn.isEnabled = true
                        } else if (response.code() == 400) {
                            holder.addBtn.text = "Dish from another restaurant cannot be added."
                            holder.addBtn.isEnabled = true
                        }
                    }

                    override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                        Log.d("Error", "Retrofit is OnFailure")
                        holder.addBtn.isEnabled = true
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return restaurantData.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val restaurantName: TextView
        val dishName: TextView
        val categoryName: TextView
        val priceValue: TextView
        val bestseller: TextView
        val dishPic: ImageView
        val addBtn: MaterialButton

        init {
            restaurantName = itemView.findViewById(R.id.restaurantKaName)
            dishName = itemView.findViewById(R.id.dishKaName)
            categoryName = itemView.findViewById(R.id.categoryKaName)
            priceValue = itemView.findViewById(R.id.priceKiValue)
            bestseller = itemView.findViewById(R.id.Bestseller)
            dishPic = itemView.findViewById(R.id.dishKaPic)
            addBtn = itemView.findViewById(R.id.addToCartBtn)
        }
    }
}