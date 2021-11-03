package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPassword2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v= inflater.inflate(R.layout.fragment_forgot_password_2, container, false)

        val verifyBtn : Button = v.findViewById(R.id.verify_button)

        verifyBtn.setOnClickListener{
            Toast.makeText(activity,"Verifying OTP",Toast.LENGTH_LONG).show()

//            val userEmail = ForgotPassword1().email
            var userEmail = "11testee22@gmail.com"
            val userOtp = v.findViewById<EditText>(R.id.editTextTextPersonName4).text.toString().trim() + v.findViewById<EditText>(R.id.editTextTextPersonName5).text.toString().trim() + v.findViewById<EditText>(R.id.editTextTextPersonName7).text.toString().trim() + v.findViewById<EditText>(R.id.editTextTextPersonName6).text.toString().trim()

//            v.findViewById<TextView>(R.id.textView2).text = userEmail

            RetrofitClient.init().verifyOtp(userEmail, userOtp).enqueue(object : Callback<DataVerifyOtpClass?> {
                override fun onResponse(call: Call<DataVerifyOtpClass?>, response: Response<DataVerifyOtpClass?>) {

                    val responseBody = response.body()
                    try {
                        if(responseBody!!.status == "OTP verified You can now change your password") {
                            Toast.makeText(activity, "OTP verified", Toast.LENGTH_LONG).show()

                            var tokenValue = responseBody.token

                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.fragment_container, ForgotPassword3())
                            fragmentTransaction?.addToBackStack(null)
                            fragmentTransaction?.commit()
                        }
                    }
                    catch(e: Exception){
                        Toast.makeText(activity,"Not",Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<DataVerifyOtpClass?>, t: Throwable) {
                    Toast.makeText(activity,"Not",Toast.LENGTH_LONG).show()
                }
            })

        }
        return v
    }
}
