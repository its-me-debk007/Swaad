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
import com.google.android.material.textfield.TextInputLayout
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForgotPassword1 : Fragment() {
    var email: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v= inflater.inflate(R.layout.fragment_forgot_password_1, container, false)
        val next_button  = v.findViewById<Button>(R.id.NextBtn)
        next_button.setOnClickListener {

//            val email = v.findViewById<EditText>(R.id.forgotPasswordEmail).text.toString().trim()
            email = "11testee22@gmail.com"
            val jsonConverter=JsonConverter(email)
            if(email.isEmpty())
            {
                v.findViewById<TextView>(R.id.forgotPasswordEmail).error="Please Enter email!"
                return@setOnClickListener
            }
            RetrofitClient.init().getOtp(jsonConverter).enqueue(object : Callback<ResponseBody?> {

                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
//                    try {
                        var status = response.message()?.toString()
                        Toast.makeText(activity,status, Toast.LENGTH_LONG).show()
                        if(status =="OK") {
                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.fragment_container, ForgotPassword2())
                            fragmentTransaction?.addToBackStack(null)
                            fragmentTransaction?.commit()
                        }

//                    }
//                    catch (e:Exception)
//                    {
//                        val e = e.toString()
//                        Toast.makeText(activity,e, Toast.LENGTH_LONG).show()
//                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(activity,t.message, Toast.LENGTH_LONG).show()
                }
            })


        }
        return v
    }


}