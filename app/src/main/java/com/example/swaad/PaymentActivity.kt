package com.example.swaad

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swaad.ApiRequests.JsonConverterOrderDetails
import com.example.swaad.ApiRequests.RecyclerAdapterManageAddress
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.ApiRequests.jsonConverterCheckout
import com.example.swaad.AuthPages.ForgotPassword2
import com.example.swaad.NavBarPages.MyCart
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCount
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishIdList
//import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.restIdList
import com.example.swaad.SplashScreen.Splash_screen
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            options.put("amount", MyCart.grantTotal*100)//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", "kunalmehrotra2001@gmail.com")
            prefill.put("contact", "9119082208")

            options.put("prefill", prefill)
            co.open(this, options)
//            for (i in  dishIdList) {
//                var restaurant_id = restIdList[i].toInt()
//                var orderDetail = ArrayList<OrderDetail>()
//                orderDetail.add(OrderDetail(dishIdList[i],dishCount[i]))
//                for (j in 0 until i) {
//                    if (restIdList[j] == restaurant_id) {
////                            var orderDetailsItem = OrderDetails(orderDetail, restaurant_id)
//                        orderDetail.add(OrderDetail(dishIdList[i], dishCount[i]))
//                    }
//                }
//                var jsonConverterOrderDetails=JsonConverterOrderDetails(restaurant_id,orderDetail)
//                RetrofitClient.init().orderUpdate(jsonConverterOrderDetails).enqueue(object : Callback<ResponseBody?> {
//                    override fun onResponse(
//                        call: Call<ResponseBody?>,
//                        response: Response<ResponseBody?>
//                    ) {
//                        Toast.makeText(PaymentActivity(), response.message().toString(), Toast.LENGTH_LONG).show()
//                    }
//
//                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                        Toast.makeText(PaymentActivity(),"Not",Toast.LENGTH_LONG).show()
//                    }
//                })
//            }
        }
        catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
        override fun onPaymentSuccess(p0: String?) {
                Toast.makeText(this, "Suceess in payment", Toast.LENGTH_LONG).show()
            val dialodView =
                LayoutInflater.from(this).inflate(R.layout.fragment_confirm_order_dialog, null)
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val mBuilder = AlertDialog.Builder(this)
                .setView(dialodView)
                .setTitle("Order Confirmation")
            mBuilder.show()
            Handler().postDelayed(
                {
                    val intent = Intent(this, NavBarActivity()::class.java)
                    startActivity(intent)
                },3000
            )
            var deleivery_id=RecyclerAdapterManageAddress.id
            var jsonConverterCheckout=jsonConverterCheckout(deleivery_id)
            var Token:String="asdasdas"
            lifecycleScope.launch{
                var AccessToken = Splash_screen.readInfo("accessToken").toString()
                Token =AccessToken
            }
            RetrofitClient.init().checkout(jsonConverterCheckout,token="Bearer $Token").enqueue(
                object : Callback<ResponseBody?> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                       if(response.isSuccessful())
                       {
                           Toast.makeText(applicationContext,"Checked Out",Toast.LENGTH_LONG).show()
                       }
                        else
                       {
                           Toast.makeText(applicationContext,"Not Checked Out",Toast.LENGTH_LONG).show()
                       }
                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        val intent = Intent(applicationContext, NavBarActivity()::class.java)
                        startActivity(intent)
                        Toast.makeText(applicationContext,"Payment was not successfull",Toast.LENGTH_LONG).show()
                    }
                }
            )
//                val fragmentManager = NavBarActivity().supportFragmentManager
//                val fragmentTransaction = fragmentManager?.beginTransaction()
//                fragmentTransaction?.replace(R.id.fragment_container,Rating_Page())
//                fragmentTransaction?.addToBackStack(null)
//                fragmentTransaction?.commit()
        }

        override fun onPaymentError(p0: Int, p1: String?) {
            Log.d("mytag", "onPaymentError: "+p1.toString())
            val intent = Intent(this, NavBarActivity()::class.java)
            startActivity(intent)
                Toast.makeText(NavBarActivity(),"Payment was not successfull ", Toast.LENGTH_LONG).show()
        }

}

