package com.example.swaad.NavBarPages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.ApiRequests.RecyclerAdapterCart
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import com.example.swaad.ApiRequests.DataClassCartInfo
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.NavBarPages.Home_page.Companion.AccessToken
import com.example.swaad.PaymentActivity
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.basePriceList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.cartList
import com.example.swaad.*
import com.example.swaad.ApiRequests.RecyclerAdapterManageAddress
import com.example.swaad.AuthPages.ReferenceSignUp
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCostList
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishCount
import com.example.swaad.RestaurantPageFiles.RecyclerAdapterRestaurantPage.Companion.dishIdList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyCart: Fragment() {
    companion object{
        var grantTotal: Int = 0
        var totalPrice: Int = 0
    }
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterCart.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(com.example.swaad.R.layout.my_cart, container, false)
        val pay_button = v.findViewById<Button>(com.example.swaad.R.id.payButton)
        var location=v.findViewById<TextView>(com.example.swaad.R.id.locationTextCart)
        if(RecyclerAdapterManageAddress.flag==1)
        {
          v.findViewById<TextView>(com.example.swaad.R.id.locationTextCart).text=RecyclerAdapterManageAddress.Adress
        }
        if(RecyclerAdapterManageAddress.flag==0)
        {
            v.findViewById<TextView>(com.example.swaad.R.id.locationTextCart).text=Home_page.adresslocation
        }

        v.findViewById<ImageView>(com.example.swaad.R.id.backBtn).setOnClickListener{
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, Home_page())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        if(dishIdList.size == 0){
            RetrofitClient.init().getCartInfo("Bearer ${AccessToken}").enqueue(object :
                Callback<DataClassCartInfo?> {
                override fun onResponse(
                    call: Call<DataClassCartInfo?>,
                    response: Response<DataClassCartInfo?>
                ) {
                        if (response.code() == 200) {
                            val order_details = response.body()?.order_details
                            if (order_details != null) {
                                for (i in 0 until order_details.size) {
                                    dishIdList.add(order_details[i].dish_id)
                                    cartList.add(order_details[i].dish_name)
                                    dishCount.add(order_details[i].quantity)
                                    dishCostList.add(order_details[i].sub_total)
                                    basePriceList.add(order_details[i].dish_price.toInt())
                                    totalPrice = response.body()!!.order_total
                                }
                            }
                        }else if(response.code() == 401){
                            Toast.makeText(
                                activity,
                                "Sorry, but token has expired!",
                                Toast.LENGTH_LONG
                            ).show()
                        }else if(response.code() == 400){
                            Toast.makeText(
                                activity,
                                "Your cart is empty!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }

                    override fun onFailure(call: Call<DataClassCartInfo?>, t: Throwable) {
                        Toast.makeText(
                            activity,
                            "Sorry! Some error has occurred! Please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }

        })
        }
//        val fragmentManager = activity?.supportFragmentManager
//        val fragmentTransaction = fragmentManager?.beginTransaction()
//        if (Build.VERSION.SDK_INT >= 26) {
//            fragmentTransaction?.setReorderingAllowed(false)
//        }
////        fragmentTransaction?.detach(this)?.attach(this)?.commit()

//        if(refreshCart) {
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(com.example.swaad.R.id.fragment_container, MyCart())
////              fragmentTransaction?.addToBackStack("fragmentLogin")
//            fragmentTransaction?.commit()
//        }

        location.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(
                com.example.swaad.R.id.fragment_container,
                Manage_Address()
            )
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        layoutManager = LinearLayoutManager(container?.context)
        val recyclerViewCart = v.findViewById<RecyclerView>(com.example.swaad.R.id.recyclerViewCart)
        recyclerViewCart.layoutManager = layoutManager
        adapter = RecyclerAdapterCart()
        recyclerViewCart.adapter = adapter

        var discountedValue = (0.4*dishCostList.sum()).toInt()
        v.findViewById<TextView>(com.example.swaad.R.id.discountValue).text = "-₹" + discountedValue.toString() + ".00"

        var totalPrice = dishCostList.sum() - discountedValue
        v.findViewById<TextView>(com.example.swaad.R.id.totalPrice).text =
            "₹" + totalPrice.toString() + ".00"

        var deliveryCharges = (0.02 * totalPrice).toInt()
        v.findViewById<TextView>(com.example.swaad.R.id.deliveryCharges).text =
            "₹" + deliveryCharges.toString() + ".00"

        grantTotal = totalPrice + deliveryCharges
        v.findViewById<TextView>(com.example.swaad.R.id.GrantTotalPrice).text =
            "₹" + grantTotal.toString() + ".00"


        pay_button.setOnClickListener{
            if(grantTotal==0)
            {
                Toast.makeText(container?.context,"Please add something in the cart !",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(activity, PaymentActivity::class.java)
            startActivity(intent)
        }
        return v
    }

//    override fun onPaymentSuccess(p0: String?) {
////        Toast.makeText(this,"Succeess in payment: ",Toast.LENGTH_LONG).show()
//    }
//
//    override fun onPaymentError(p0: Int, p1: String?) {
////        Toast.makeText(this,"Error in payment: ",Toast.LENGTH_LONG).show()
//    }
//    fun makePayment()
//    {
//        /*
//            *  You need to pass current activity in order to let Razorpay create CheckoutActivity
//            * */
////        val activity: Activity =
//        val co = Checkout()
//
//        try {
//            val options = JSONObject()
//            options.put("name","Swaad")
//            options.put("description","Food Charges")
//            //You can omit the image option to fetch the image from dashboard
//            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
//            options.put("theme.color", "#3399cc");
//            options.put("currency","INR");
//            options.put("order_id", "order_DBJOWzybf0sJbb");
//            options.put("amount","50000")//pass amount in currency subunits
//
//            val retryObj = JSONObject();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);
//
//            val prefill = JSONObject()
//            prefill.put("email","gaurav.kumar@example.com")
//            prefill.put("contact","9876543210")
//
//            options.put("prefill",prefill)
//            co.open(activity,options)
//        }catch (e: Exception){
//            Toast.makeText(NavBarActivity(),"Error in payment please try again",Toast.LENGTH_LONG).show()
//            e.printStackTrace()
//        }
//    }
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


