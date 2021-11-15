package com.example.swaad.NavBarPages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.swaad.RecyclerAdapterCart
import com.example.swaad.RecyclerAdapterCart.Companion.itemRemoved
import com.example.swaad.RecyclerAdapterCart.Companion.pos
import com.example.swaad.SearchPage2Files.RecyclerAdapterSearchPage
import org.json.JSONObject

import android.R

import com.razorpay.Checkout

import android.app.Activity
import android.widget.Toast
import com.example.swaad.NavBarActivity
import java.lang.Exception


class MyCart: Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(com.example.swaad.R.layout.my_cart, container, false)
    val pay_button=v.findViewById<Button>(com.example.swaad.R.id.payButton)
        pay_button.setOnClickListener(View.OnClickListener {
            PaymentNow("100")
        })
        layoutManager = LinearLayoutManager(container?.context)
        val recyclerViewCart = v.findViewById<RecyclerView>(com.example.swaad.R.id.recyclerViewCart)
        recyclerViewCart.layoutManager = layoutManager
        adapter = RecyclerAdapterCart()
        recyclerViewCart.adapter = adapter

        if(itemRemoved){
            RecyclerAdapterCart().notifyItemRemoved(pos)
            itemRemoved = false
        }
//        val itemCount: TextView = v.findViewById(R.id.itemCount)
//
//        v.findViewById<ImageView>(R.id.minus).setOnClickListener{
//            if(itemCount.text.toString() != "0"){
//                var amount = itemCount.text.toString().toInt()
//                amount--
//                itemCount.text = amount.toString()
//            }
//        }
//        v.findViewById<ImageView>(R.id.plus).setOnClickListener{
//            var amount = itemCount.text.toString().toInt()
//            amount++
//            itemCount.text = amount.toString()
//        }

        return v
    }

    private fun PaymentNow(amount: String) {
        val activity: Activity = NavBarActivity()
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_1gvZaZejIcdjGI")
        checkout.setImage(com.example.swaad.R.drawable.ic_launcher_background)
        val finalAmount = (amount.toFloat() * 100).toDouble()
        try {
            val options = JSONObject()
            options.put("name", "TRAIDEV")
            options.put("description", "Reference No. #123456")
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", finalAmount.toString() + "") //300 X 100
            options.put("prefill.email", "kunalmehrotra2001@gmail.com")
            options.put("prefill.contact", "")
            checkout.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()
        }
    }
}