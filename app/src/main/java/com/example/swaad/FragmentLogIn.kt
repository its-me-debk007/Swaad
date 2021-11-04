package com.example.swaad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Retrofit

class FragmentLogIn: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_login, container, false)

        val signUp : TextView = v.findViewById(R.id.loginSignUpText)
        signUp.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,ReferenceSignUp())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        val forgotPassword : TextView = v.findViewById(R.id.loginForgotPasswordText)
        forgotPassword.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,ForgotPassword1())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        val signInBtn : Button = v.findViewById(R.id.loginSignInBtn)
        signInBtn.setOnClickListener {

            val userEmail = v.findViewById<TextInputEditText>(R.id.loginEmail2).text.toString().trim()
            val userPassword = v.findViewById<TextInputEditText>(R.id.loginPassword2).text.toString().trim()

            Toast.makeText(activity,"Logging In",Toast.LENGTH_LONG).show()

            RetrofitClient.init().logInUser(userEmail, userPassword).enqueue(object : Callback<DataClass?> {
                override fun onResponse(call: Call<DataClass?>, response: Response<DataClass?>) {

                    val responseBody = response.body()
                    try {
                        responseBody!!.token
                        Toast.makeText(activity,"You have been logged in",Toast.LENGTH_LONG).show()
                    }
                    catch(e: Exception){
                        Toast.makeText(activity,"Wrong Credentials!!\n\nPlease check your email/password and try again!",Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<DataClass?>, t: Throwable) {
                    Toast.makeText(activity,"Wrong Credentials!!\n\nPlease check your email/password and try again!",Toast.LENGTH_LONG).show()
                }
            })

        }

        return v
    }
}