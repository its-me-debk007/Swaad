package com.example.swaad.ApiRequests

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        holder.itemName.text = cartList[holder.getAdapterPosition()]
        holder.dishPrice.text = "₹" + dishCostList[holder.getAdapterPosition()].toString() + ".00"
        holder.plus.setImageResource(pluses[holder.getAdapterPosition()])
        holder.minus.setImageResource(minuses[holder.getAdapterPosition()])
        holder.itemCount.text = dishCount[holder.getAdapterPosition()].toString()

        holder.plus.setOnClickListener {


            RetrofitClient.init().addToCart(dishIdList[holder.getAdapterPosition()]).enqueue(object :
                Callback<DataClassAddedToCart?> {
                override fun onResponse(
                    call: Call<DataClassAddedToCart?>,
                    response: Response<DataClassAddedToCart?>
                ) {
                    if (response.code() == 200) {
                        dishCount[holder.getAdapterPosition()]++
                        holder.itemCount.text = dishCount[holder.getAdapterPosition()].toString()
                        holder.dishPrice.text = "₹" + (basePriceList[holder.getAdapterPosition()] * dishCount[holder.getAdapterPosition()]).toString() + ".00"
                    }
                    else if(response.code() == 400){
                        Log.d("Error", "Another restaurant dish added!")
                    }
                }
                override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                    Log.d("Error", "Retrofit is OnFailure")
                }
            })
        }
        holder.minus.setOnClickListener {

            if (dishCount[holder.getAdapterPosition()] > 0) {

                RetrofitClient.init().removeFromCart(dishIdList[holder.absoluteAdapterPosition]).enqueue(object :
                    Callback<DataClassAddedToCart?> {
                    override fun onResponse(
                        call: Call<DataClassAddedToCart?>,
                        response: Response<DataClassAddedToCart?>
                    ) {
                        if (response.code() == 200) {
                            dishCount[holder.getAdapterPosition()]--
                            holder.itemCount.text =
                                dishCount[holder.getAdapterPosition()].toString()
                            holder.dishPrice.text =
                                "₹" + (basePriceList[holder.getAdapterPosition()] * dishCount[holder.getAdapterPosition()]).toString() + ".00"
                        } else if (response.code() == 200) {

                        }
                    }

                        override fun onFailure(call: Call<DataClassAddedToCart?>, t: Throwable) {
                            Log.d("Error", "Retrofit is OnFailure")
                        }
                })


            } else {

                RetrofitClient.init().removeFromCart(dishIdList[holder.absoluteAdapterPosition]).enqueue(object :
                    Callback<DataClassAddedToCart?> {
                    override fun onResponse(
                        call: Call<DataClassAddedToCart?>,
                        response: Response<DataClassAddedToCart?>
                    ) {
                        if (response.code() == 200) {
                            dishCount.removeAt(holder.absoluteAdapterPosition)
                            cartList.removeAt(holder.absoluteAdapterPosition)
                            dishCostList.removeAt(holder.absoluteAdapterPosition)
                            basePriceList.removeAt(holder.absoluteAdapterPosition)
                            dishIdList.removeAt(holder.absoluteAdapterPosition)
                            notifyDataSetChanged()
                        }
                        else if (response.code() == 200){

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

