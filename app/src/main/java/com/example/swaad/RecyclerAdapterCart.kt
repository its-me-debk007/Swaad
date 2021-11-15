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
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCostList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishNameList

class RecyclerAdapterCart: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>()  {

    companion object{
        var itemRemoved: Boolean = false
        var pos: Int = 0
    }
//    val dishesNameList = dishNameList
//    val dishesCostList = dishCostList
    val pluses = intArrayOf(R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus)
    val minuses = intArrayOf(R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterCart.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterCart.ViewHolder, position: Int) {
        holder.itemName.text = dishNameList[position]
        holder.dishPrice.text = dishCostList[position]
        holder.plus.setImageResource(pluses[position])
        holder.minus.setImageResource(minuses[position])
        holder.itemCount.text = "1"

        holder.plus.setOnClickListener {
            var amount = holder.itemCount.text.toString().toInt()
            amount++
            holder.itemCount.text = amount.toString()
            holder.dishPrice.text = "₹" + (amount * basePriceList[position]).toString() + ".00"
        }
        holder.minus.setOnClickListener {
            var amount = holder.itemCount.text.toString().toInt()
            if (amount != 1) {
                amount--
                holder.itemCount.text = amount.toString()
                holder.dishPrice.text = "₹" + (amount * basePriceList[position]).toString() + ".00"
            } else {
                dishNameList.remove(holder.itemName.text)
                dishCostList.remove(holder.dishPrice.text)
                itemRemoved = true


                //                    val fragmentManager = activity?.supportFragmentManager
                //                    val fragmentTransaction = fragmentManager?.beginTransaction()
                //                    fragmentTransaction?.replace(R.id.fragment_container, MyCart())
                //                    fragmentTransaction?.addToBackStack(null)
                //                    fragmentTransaction?.commit()
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

