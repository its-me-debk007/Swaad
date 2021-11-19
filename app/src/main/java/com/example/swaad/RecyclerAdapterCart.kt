package com.example.swaad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.NavBarPages.MyCart
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.basePriceList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.cartList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCostList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCount

class RecyclerAdapterCart: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>()  {

    private val pluses = intArrayOf(R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus)
    private val minuses = intArrayOf(R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterCart.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterCart.ViewHolder, position: Int) {
        holder.itemName.text = cartList[position]
        holder.dishPrice.text = "₹" + dishCostList[position] + ".00"
        holder.plus.setImageResource(pluses[position])
        holder.minus.setImageResource(minuses[position])
        holder.itemCount.text = dishCount[position].toString()

        holder.plus.setOnClickListener {
            dishCount[position]++
            holder.itemCount.text = dishCount[position].toString()
            holder.dishPrice.text = "₹" + (basePriceList[position] * dishCount[position]).toString() + ".00"
        }
        holder.minus.setOnClickListener {
            dishCount[position]--
            if (dishCount[position] != 0) {
                holder.itemCount.text = dishCount[position].toString()
                holder.dishPrice.text = "₹" + (basePriceList[position] * dishCount[position]).toString() + ".00"
            } else {
                dishCount.removeAt(position)
                cartList.removeAt(position)
                dishCostList.removeAt(position)
                basePriceList.removeAt(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dishCostList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemName: TextView
        val dishPrice: TextView
        val itemCount: TextView
        val plus: ImageView
        val minus: ImageView

        init {
            itemName = itemView.findViewById(R.id.itemName)
            dishPrice = itemView.findViewById(R.id.dishPrice)
            itemCount = itemView.findViewById(R.id.itemCount)
            plus = itemView.findViewById(R.id.plus)
            minus = itemView.findViewById(R.id.minus)

        }
    }
}

