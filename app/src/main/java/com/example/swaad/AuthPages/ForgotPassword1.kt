package com.example.swaad.AuthPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.swaad.AuthPages.ReferenceSignUp.Companion.nextPage
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.swaad.ApiRequests.JsonConverter
import com.example.swaad.R
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.otp_sign_up
import com.example.swaad.otp_sign_up2
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForgotPassword1 : Fragment() {

    private fun isValidEmail(str: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }

    companion object{
        lateinit var email:String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v= inflater.inflate(R.layout.fragment_forgot_password_1, container, false)
        val progressBar = v.findViewById<ProgressBar>(R.id.progressBar3)
        val next_button  = v.findViewById<Button>(R.id.NextBtn)

        next_button.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            next_button.isEnabled = false
            next_button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background2))

            email = v.findViewById<TextInputEditText>(R.id.forgotPasswordEmail2).text.toString().trim()

            if(!isValidEmail(email))
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<TextView>(R.id.forgotPasswordEmail2).error="Please enter a valid email"
                next_button.isEnabled = true
                next_button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
//            Toast.makeText(activity,"Please wait !", Toast.LENGTH_LONG).show()

            val jsonConverter= JsonConverter(email)
            RetrofitClient.init().getOtp(jsonConverter).enqueue(object : Callback<ResponseBody?> {

                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
//                        val status = response.message().toString()
//                        Toast.makeText(activity,status, Toast.LENGTH_LONG).show()
                        if(response.code() == 200) {
                            nextPage = "forgotPassword"
                            progressBar.visibility=View.INVISIBLE
                            Toast.makeText(activity,"OTP has been sent to your email", Toast.LENGTH_LONG).show()
                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.fragment_container, ForgotPassword2())
                            fragmentTransaction?.addToBackStack(null)
                            fragmentTransaction?.commit()
                        }
                        else if(response.code() == 406){
                            progressBar.visibility=View.INVISIBLE
                            Toast.makeText(activity,"Please verify your account first before changing your password",Toast.LENGTH_LONG).show()
//                            val fragmentManager = activity?.supportFragmentManager
//                            val fragmentTransaction = fragmentManager?.beginTransaction()
//                            fragmentTransaction?.replace(R.id.fragment_container, otp_sign_up2())
//                            fragmentTransaction?.addToBackStack(null)
//                            fragmentTransaction?.commit()
                        }
                        else
                        {
                            progressBar.visibility=View.INVISIBLE
                            Toast.makeText(activity,"Email is not registered",Toast.LENGTH_LONG).show()
                            next_button.isEnabled = true
                            next_button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                        }

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,t.message, Toast.LENGTH_LONG).show()
                }
            })

        }
        return v
    }

}