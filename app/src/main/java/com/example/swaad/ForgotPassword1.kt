package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForgotPassword1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_forgot_password_1, container, false)
        val next_button  = v.findViewById<Button>(R.id.NextBtn)
        next_button.setOnClickListener {
            val email=v.findViewById<TextView>(R.id.forgotPasswordEmail).text.toString().trim()
            val jsonConverter=JsonConverter(email)
            RetrofitClient.init().getOtp(jsonConverter).enqueue(object : Callback<DataClassOtp?> {

                override fun onResponse(
                    call: Call<DataClassOtp?>,
                    response: Response<DataClassOtp?>
                ) {
//                    try {
                        val status = response.body()?.status.toString()

                        Toast.makeText(activity,status,Toast.LENGTH_LONG).show()
                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container,ForgotPassword2())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
//                    }
//                    catch (e:Exception)
//                    {
//                        val e = e.toString()
//                        Toast.makeText(activity,e, Toast.LENGTH_LONG).show()
//                    }
                }

                override fun onFailure(call: Call<DataClassOtp?>, t: Throwable) {
                    Toast.makeText(activity,t.message, Toast.LENGTH_LONG).show()

                }
            })


        }
        return v
    }

}