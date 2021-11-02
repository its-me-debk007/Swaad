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

        val verify_button : Button = v.findViewById(R.id.verify_button)

        verify_button.setOnClickListener{
            Toast.makeText(activity,"Verifying OTP",Toast.LENGTH_LONG).show()

//            val userEmail = v.findViewById<TextView>(R.id.loginEmail).text.toString().trim()
            val userEmail = "11testee11@gmail.com"
            val userOtp = v.findViewById<EditText>(R.id.editTextTextPersonName4).text.toString().trim() + v.findViewById<EditText>(R.id.editTextTextPersonName5).text.toString().trim() + v.findViewById<EditText>(R.id.editTextTextPersonName7).text.toString().trim() + v.findViewById<EditText>(R.id.editTextTextPersonName6).text.toString().trim()
            val jsonConverter = OtpJsonConverter(userEmail, userOtp)

//            v.findViewById<TextView>(R.id.textView4).text = userOtp

            RetrofitClient.init().verifyOtp(jsonConverter).enqueue(object :
                Callback<DataVerifyOtpClass?> {
                override fun onResponse(call: Call<DataVerifyOtpClass?>, response: Response<DataVerifyOtpClass?>) {

                    val responseBody = response.body()
                    try {
//                        responseBody!!.detail
                        Toast.makeText(activity,"Your OTP has been verified!", Toast.LENGTH_LONG).show()

                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container,ForgotPassword3())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    }
                    catch(e: Exception){
                        Toast.makeText(activity,"Your OTP has not been verified!",
                            Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<DataVerifyOtpClass?>, t: Throwable) {
                    Toast.makeText(activity,"Your OTP has not been verified!",
                        Toast.LENGTH_LONG).show()
                }
            })



        }
        return v
    }
}
