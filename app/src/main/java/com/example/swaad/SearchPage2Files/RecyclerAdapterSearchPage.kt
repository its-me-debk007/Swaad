package com.example.swaad.SearchPage2Files

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.*
import com.example.swaad.NavBarPages.Home_page.Companion.responseDataKunal
import com.example.swaad.NavBarPages.Home_page.Companion.status
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.basePriceList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.cartList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCostList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCount
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishIdList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.restIdList
import com.example.swaad.SearchPage2Files.SearchPage2.Companion.responseDataDebashish
import com.google.android.material.button.MaterialButton

class RecyclerAdapterSearchPage(val context: Context, val restaurantData: List<DataGetDishesList>): RecyclerView.Adapter<RecyclerAdapterSearchPage.ViewHolder>() {

    companion object{
        var toastMaker: Boolean = false
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
            holder.restaurantName.text = "By " + restaurantData[position].restaurant_name
            holder.dishName.text = restaurantData[position].title
            holder.categoryName.text = "In " + restaurantData[position].category
            holder.priceValue.text = "₹" + restaurantData[position].price.toString() +"0"
//        holder.bestseller.text = bestsellers[position]
//        holder.dishPic.setImageResource(dishPics[position])

        holder.addBtn.setOnClickListener {
            if (cartList.size < 5) {
                var flag = 0
                for (i in 0 until dishIdList.size) {
                    if (dishIdList[i] == restaurantData[position].id) {
                        flag = 1
                        pos = i
                        break
                    }
                }
                if (flag == 0) {
                    cartList.add(holder.dishName.text.toString())
                    dishCostList.add(restaurantData[position].price)
                    basePriceList.add(restaurantData[position].price)
                    dishCount.add(1)
                    dishIdList.add(restaurantData[position].id)
                    restIdList.add(restaurantData[position].restaurant_id)
                } else {

                    dishCount[pos]++
                    dishCostList[pos] = dishCount[pos] * basePriceList[pos]
                }
            } else {
                Toast.makeText(context,"You have added the maximum number of elements in the cart", Toast.LENGTH_LONG).show()
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