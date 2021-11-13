package com.example.swaad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterSearchPage: RecyclerView.Adapter<RecyclerAdapterSearchPage.ViewHolder>() {

    val restaurants = arrayOf("By Roma cafe n Dinner1","By Roma cafe n Dinner2","By Roma cafe n Dinner3", "By Roma cafe n Dinner4", "By Roma cafe n Dinner5")
    val dishes = arrayOf("Tandori chicken", "Tandori chicken", "Tandori chicken", "Tandori chicken", "Tandori chicken")
    val categories = arrayOf("In Main Course", "In Main Course", "In Main Course", "In Main Course", "In Main Course")
    val prices = arrayOf("₹300.00", "₹300.00", "₹300.00", "₹300.00", "₹300.00")
    val bestsellers = arrayOf("Bestseller", "Bestseller", "Bestseller", "Bestseller", "Bestseller")
    val dishPics = intArrayOf(R.drawable.ic_cart, R.drawable.ic_cart, R.drawable.ic_cart, R.drawable.ic_cart, R.drawable.ic_cart)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterSearchPage.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_page2_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterSearchPage.ViewHolder, position: Int) {
        holder.restaurantName.text = restaurants[position]
        holder.dishName.text = dishes[position]
        holder.categoryName.text = categories[position]
        holder.priceValue.text = prices[position]
        holder.bestseller.text = bestsellers[position]
        holder.dishPic.setImageResource(dishPics[position])
    }

    override fun getItemCount(): Int {
        return restaurants.size
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