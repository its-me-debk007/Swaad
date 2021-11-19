package com.example.swaad

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.ForgotPassword2
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishIdList
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class PaymentActivity:AppCompatActivity(),PaymentResultListener
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Checkout.preload(applicationContext)
//        setContentView(R.layout.redirectingpayment)
        makePayment()
    }
    fun makePayment() {
        try {
            val co = Checkout()
            Checkout.preload(this)
            co.setKeyID("rzp_test_pPyQDwFu6wozYU")
            val options = JSONObject()
            options.put("name", "Swaad")
            options.put("description", "Food Charges")
            //You can omit the image option to fetch the image from dashboard
//            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
        //    options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount", "500000")//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", "gaurav.kumar@example.com")
            prefill.put("contact", "9876543210")

            options.put("prefill", prefill)
            co.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG)
                .show()
            e.printStackTrace()
        }
    }


        override fun onPaymentSuccess(p0: String?) {
                Toast.makeText(this, "Suceess in payment", Toast.LENGTH_LONG).show()
                for (i in 0 until dishIdList.size)
                {
                    for(j in 0 until restId.size )
                    {

                    }
                    val order = List<OrderDetail>
                    order.add(dish_id,capacity)
                    val orderDetail=OrderDetails(order, restaurant_id  )

                }
                val jsonConverterOrderDetails=
//                RetrofitClient.init().orderUpdate()
//                val fragmentManager = NavBarActivity().supportFragmentManager
//                val fragmentTransaction = fragmentManager?.beginTransaction()
//                fragmentTransaction?.replace(R.id.fragment_container,Rating_Page())
//                fragmentTransaction?.addToBackStack(null)
//                fragmentTransaction?.commit()
        }

        override fun onPaymentError(p0: Int, p1: String?) {
            Log.d("mytag", "onPaymentError: "+p1.toString())
                Toast.makeText(NavBarActivity(),"Error in payment "+p1.toString(), Toast.LENGTH_LONG).show()

        }

}

