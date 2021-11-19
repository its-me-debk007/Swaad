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
import com.example.swaad.SearchPage2Files.RecyclerAdapterSearchPage.Companion.pos

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
        holder.itemName.text = cartList[pos]
        holder.dishPrice.text = "₹" + dishCostList[pos] + ".00"
        holder.plus.setImageResource(pluses[pos])
        holder.minus.setImageResource(minuses[pos])
        holder.itemCount.text = dishCount[pos].toString()

        holder.plus.setOnClickListener {
            dishCount[pos]++
            holder.itemCount.text = dishCount[pos].toString()
            holder.dishPrice.text = "₹" + (basePriceList[pos] * dishCount[pos]).toString() + ".00"
            RecyclerAdapterCart().notifyDataSetChanged()

        }
        holder.minus.setOnClickListener {
            dishCount[pos]--
            if (dishCount[pos] != 0) {
                holder.itemCount.text = dishCount[pos].toString()
                holder.dishPrice.text = "₹" + (basePriceList[pos] * dishCount[pos]).toString() + ".00"
            } else {
                dishCount.removeAt(pos)
                cartList.removeAt(pos)
                dishCostList.removeAt(pos)
                basePriceList.removeAt(pos)
//                RecyclerAdapterCart().notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
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

