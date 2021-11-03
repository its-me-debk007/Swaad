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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForgotPassword3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_forgot_password_3, container, false)

        val resetButton : Button = v.findViewById(R.id.button3)
        resetButton.setOnClickListener{

//            val newPassword = v.findViewById<TextInputLayout>(R.id.editTextTextPersonName8).text.toString().trim()
            val newPassword = "admin"
            val tokenValue = "6496bfdba61fccc9837a4e71dec6dcd831c74567"
            val tokenString = "Token " + tokenValue

            Toast.makeText(activity,"Setting new Password", Toast.LENGTH_LONG).show()

            RetrofitClient.init().setNewPassword(newPassword, tokenString).enqueue(object :
                Callback<DataSetNewPasswordClass?> {
                override fun onResponse(call: Call<DataSetNewPasswordClass?>, response: Response<DataSetNewPasswordClass?>) {

                    val responseBody = response.body()
                    try {
                        responseBody!!.name
                        Toast.makeText(activity,"New Password set", Toast.LENGTH_LONG).show()
                    }
                    catch(e: Exception){
                        Toast.makeText(activity,"New Password not set",
                            Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<DataSetNewPasswordClass?>, t: Throwable) {
                    Toast.makeText(activity,"New Password not set",
                        Toast.LENGTH_LONG).show()
                }
            })


        }

        return v
    }

}