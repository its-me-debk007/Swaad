package com.example.swaad.LayoutPages

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.swaad.LayoutPages.ReferenceSignUp.Companion.nextPage
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.swaad.JsonConverter
import com.example.swaad.R
import com.example.swaad.ApiRequest.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForgotPassword1 : Fragment() {

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

            if(email.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<TextView>(R.id.forgotPasswordEmail).error="Please Enter email!"
                next_button.isEnabled = false
                next_button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background2))
                return@setOnClickListener
            }
//            Toast.makeText(activity,"Please wait !", Toast.LENGTH_LONG).show()

            val jsonConverter= JsonConverter(email)
            RetrofitClient.init().getOtp(jsonConverter).enqueue(object : Callback<ResponseBody?> {

                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    try {
                        val status = response.message().toString()
//                        Toast.makeText(activity,status, Toast.LENGTH_LONG).show()
                        if(status =="OK") {
                            nextPage = "forgotPassword"
                            progressBar.visibility=View.INVISIBLE
                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.fragment_container, ForgotPassword2())
                            fragmentTransaction?.addToBackStack(null)
                            fragmentTransaction?.commit()
                        }
                        else
                        {
                            progressBar.visibility=View.INVISIBLE
                            Toast.makeText(activity,status,Toast.LENGTH_LONG).show()
                            next_button.setEnabled(true);
                            next_button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                        }
                    }
                    catch (e:Exception)
                    {
                        val e = e.toString()
                        Toast.makeText(activity,e, Toast.LENGTH_LONG).show()
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