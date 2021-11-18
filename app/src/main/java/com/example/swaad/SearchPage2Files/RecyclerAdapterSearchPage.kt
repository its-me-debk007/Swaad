package com.example.swaad.SearchPage2Files

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.DataGetRestaurantNames
import com.example.swaad.R
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.cartList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCostList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCount

class RecyclerAdapterSearchPage(val context: Context,val restaurantData: List<DataGetRestaurantNames>): RecyclerView.Adapter<RecyclerAdapterSearchPage.ViewHolder>() {

//    private val restaurants = arrayOf("By Roma cafe n Dinner1","By Roma cafe n Dinner2","By Roma cafe n Dinner3", "By Roma cafe n Dinner4", "By Roma cafe n Dinner5")
//    private val dishes = arrayOf("Tandori chicken1", "Tandori chicken2", "Tandori chicken3", "Tandori chicken4", "Tandori chicken5")
//    private val categories = arrayOf("In Main Course", "In Main Course", "In Main Course", "In Main Course", "In Main Course")
    private val prices = arrayOf(
        "₹301.00",
        "₹302.00",
        "₹303.00",
        "₹304.00",
        "₹305.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00",
        "₹302.00",
        "₹303.00")
//    private val bestsellers = arrayOf("Bestseller", "Bestseller", "Bestseller", "Bestseller", "Bestseller")
//    private val dishPics = intArrayOf(
//        R.drawable.ic_cart,
//        R.drawable.ic_cart,
//        R.drawable.ic_cart,
//        R.drawable.ic_cart,
//        R.drawable.ic_cart,
//        R.drawable.ic_cart,
//        R.drawable.ic_cart,
//        R.drawable.ic_cart,
//        R.drawable.ic_cart
//    )

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
        holder.dishName.text = restaurantData[position].rest_name
//        holder.categoryName.text = categories[position]
        holder.priceValue.text = prices[position]
//        holder.bestseller.text = bestsellers[position]
//        holder.dishPic.setImageResource(dishPics[position])

        holder.addBtn.setOnClickListener {
            var flag = 0
            for (dish in cartList) {
                if (dish == holder.dishName.text.toString()) {
                    flag = 1
                    break
                }
            }
            if (flag == 0) {
                cartList.add(holder.dishName.text.toString())
                dishCostList.add(holder.priceValue.text.toString())
                dishCount.add(position, 1)
            } else
                dishCount[position]++
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
        val addBtn: Button

        init {
            restaurantName = itemView.findViewById(R.id.restaurantKaName)
            dishName = itemView.findViewById(R.id.dishKaName)
            categoryName = itemView.findViewById(R.id.categoryKaName)
            priceValue = itemView.findViewById(R.id.priceKiValue)
            bestseller = itemView.findViewById(R.id.Bestseller)
            dishPic = itemView.findViewById(R.id.dishKaPic)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}