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
import com.example.swaad.SearchPage2Files.RecyclerAdapterSearchPage
import org.json.JSONObject

import android.R

//import com.razorpay.Checkout

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.swaad.MainActivity
import com.example.swaad.NavBarActivity
import com.example.swaad.PaymentActivity
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import java.lang.Exception


class MyCart: Fragment(),PaymentResultListener {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(com.example.swaad.R.layout.my_cart, container, false)
        val pay_button = v.findViewById<Button>(com.example.swaad.R.id.payButton)
        pay_button.setOnClickListener{
            val intent = Intent(activity, PaymentActivity::class.java)
            startActivity(intent)
//            PaymentNow("100")
//            Checkout.preload(container?.context)
        }
        layoutManager = LinearLayoutManager(container?.context)
        val recyclerViewCart = v.findViewById<RecyclerView>(com.example.swaad.R.id.recyclerViewCart)
        recyclerViewCart.layoutManager = layoutManager
        adapter = RecyclerAdapterCart()
        recyclerViewCart.adapter = adapter

//        if (itemRemoved) {
//            RecyclerAdapterCart().notifyItemRemoved(pos)
//            itemRemoved = false
//        }
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

    override fun onPaymentSuccess(p0: String?) {
//        Toast.makeText(this,"Succeess in payment: ",Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
//        Toast.makeText(this,"Error in payment: ",Toast.LENGTH_LONG).show()
    }
    fun makePayment()
    {
        /*
            *  You need to pass current activity in order to let Razorpay create CheckoutActivity
            * */
//        val activity: Activity =
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Swaad")
            options.put("description","Food Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","50000")//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(NavBarActivity(),"Error in payment please try again",Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}

//    private fun PaymentNow(amount: String) {
//        val activity: Activity = NavBarActivity()
//        val checkout = Checkout()
//        checkout.setKeyID("rzp_test_1gvZaZejIcdjGI")
//        checkout.setImage(com.example.swaad.R.drawable.ic_launcher_background)
//        val finalAmount = (amount.toFloat() * 100).toDouble()
//        try {
//            val options = JSONObject()
//            options.put("name", "TRAIDEV")
//            options.put("description", "Reference No. #123456")
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
//            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
//            options.put("theme.color", "#3399cc")
//            options.put("currency", "INR")
//            options.put("amount", finalAmount.toString() + "") //300 X 100
//            options.put("prefill.email", "kunalmehrotra2001@gmail.com")
//            options.put("prefill.contact", "")
//            checkout.open(activity,options)
//        } catch (e: Exception) {
//            Toast.makeText(activity,e.message,Toast.LENGTH_LONG).show()
//        }
//    }
//fun makePayment()
//{
//    /*
//        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
//        * */
//    val activity: Activity = this
//    val co = Checkout()
//
//    try {
//        val options = JSONObject()
//        options.put("name","Razorpay Corp")
//        options.put("description","Demoing Charges")
//        //You can omit the image option to fetch the image from dashboard
//        options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
//        options.put("theme.color", "#3399cc");
//        options.put("currency","INR");
//        options.put("order_id", "order_DBJOWzybf0sJbb");
//        options.put("amount","50000")//pass amount in currency subunits
//
//        val retryObj = JSONObject();
//        retryObj.put("enabled", true);
//        retryObj.put("max_count", 4);
//        options.put("retry", retryObj);
//
//        val prefill = JSONObject()
//        prefill.put("email","gaurav.kumar@example.com")
//        prefill.put("contact","9876543210")
//
//        options.put("prefill",prefill)
//        co.open(activity,options)
//    }catch (e: Exception){
//        Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
//        e.printStackTrace()
//    }
//}


