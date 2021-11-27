package com.example.swaad.ApiRequests

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.NavBarPages.Home_page.Companion.AccessToken
import com.example.swaad.R
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.basePriceList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.cartList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCostList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCount
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishIdList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerAdapterCart: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>()  {

    private val pluses = intArrayOf(
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus,
        R.drawable.ic_plus
    )
    private val minuses = intArrayOf(
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus,
        R.drawable.ic_minus
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = cartList[position]
        holder.dishPrice.text = "₹" + dishCostList[position].toString() + ".00"
        holder.plus.setImageResource(pluses[position])
        holder.minus.setImageResource(minuses[position])
        holder.itemCount.text = dishCount[position].toString()
//        notifyDataSetChanged()

        holder.plus.setOnClickListener {
            holder.plus.isEnabled = false
            RetrofitClient.init().addToCart("Bearer ${AccessToken}", dishIdList[position]).enqueue(object :
                Callback<DataClassAddedToCart?> {
                override fun onResponse(
                    call: Call<DataClassAddedToCart?>,
                    response: Response<DataClassAddedToCart?>
                ) {
                    if (response.code() == 200) {
                        dishCount[position]++
                        holder.itemCount.text = dishCount[position].toString()
                        holder.dishPrice.text = "₹" + (basePriceList[position] * dishCount[holder.getAdapterPosition()]).toString() + ".00"
                        holder.plus.isEnabled = true
                    }
                    else if(response.code() == 400){
                        Log.d("Error", "Another restaurant dish added!")
                        holder.plus.isEnabled = true
                    }
                }
                override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                    Log.d("Error", "Retrofit is OnFailure")
                    holder.plus.isEnabled = true
                }
            })
        }
        holder.minus.setOnClickListener {

            holder.minus.isEnabled = false
            if (dishCount[position] > 1) {

                RetrofitClient.init().removeFromCart("Bearer ${AccessToken}", dishIdList[position]).enqueue(object :
                    Callback<DataClassAddedToCart?> {
                    override fun onResponse(
                        call: Call<DataClassAddedToCart?>,
                        response: Response<DataClassAddedToCart?>
                    ) {
                        if (response.code() == 200) {
                            dishCount[position]--
                            holder.itemCount.text =
                                dishCount[position].toString()
                            holder.dishPrice.text =
                                "₹" + (basePriceList[position] * dishCount[position]).toString() + ".00"
                            holder.minus.isEnabled = true
                        }else{
                            holder.minus.isEnabled = true
                        }
                    }

                    override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                            Log.d("Error", "Retrofit is OnFailure")
                            holder.minus.isEnabled = true
                        }
                })


            } else {

                RetrofitClient.init().removeFromCart("Bearer ${AccessToken}", dishIdList[position]).enqueue(object :
                    Callback<DataClassAddedToCart?> {
                    override fun onResponse(
                        call: Call<DataClassAddedToCart?>,
                        response: Response<DataClassAddedToCart?>
                    ) {
                        if (response.code() == 200) {
                            dishCount.removeAt(position)
                            cartList.removeAt(position)
                            dishCostList.removeAt(position)
                            basePriceList.removeAt(position)
                            dishIdList.removeAt(position)
                            notifyDataSetChanged()
                            holder.minus.isEnabled = true
                        }
                        else{
                            holder.minus.isEnabled = true
                        }
                    }
                    override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                        Log.d("Error", "Retrofit is OnFailure")
                        holder.minus.isEnabled = true
                    }
                })

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

