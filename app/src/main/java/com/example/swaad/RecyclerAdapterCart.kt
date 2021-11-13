package com.example.swaad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterCart: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>()  {
    val dishesNameList = arrayOf("Jalebi", "Imarti", "Rasmalai")
    val dishesCostList = arrayOf("Rs332", "Rs535", "Rs211")
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
        holder.itemName.text = dishesNameList[position]
        holder.dishPrice.text = dishesCostList[position]
        holder.plus.setImageResource(pluses[position])
        holder.minus.setImageResource(minuses[position])
        holder.itemCount.text = "1"

        holder.plus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var amount = holder.itemCount.text.toString().toInt()
                amount++
                holder.itemCount.text = amount.toString()
            }
        })
        holder.minus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var amount = holder.itemCount.text.toString().toInt()
                if(amount != 1)
                    amount--
                holder.itemCount.text = amount.toString()
            }
        })
    }

    override fun getItemCount(): Int {
        return dishesCostList.size
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

