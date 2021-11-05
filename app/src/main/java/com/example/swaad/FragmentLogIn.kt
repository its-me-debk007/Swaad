package com.example.swaad

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import retrofit2.Retrofit

class FragmentLogIn: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_login, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBar)
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
            progressBar.visibility=View.VISIBLE
            val userEmail = v.findViewById<TextInputEditText>(R.id.loginEmail2).text.toString().trim()

            val userPassword = v.findViewById<TextInputEditText>(R.id.loginPassword2).text.toString().trim()

            if(userEmail.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<EditText>(R.id.loginEmail2).error="Email can not be Empty"
                return@setOnClickListener

            }
            if(userPassword.isEmpty())
            {

                progressBar.visibility=View.INVISIBLE
                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar2)
                v.findViewById<EditText>(R.id.loginPassword2).error="Please enter the password"
                return@setOnClickListener

            }
            Toast.makeText(activity,"Logging In",Toast.LENGTH_LONG).show()

            RetrofitClient.init().logInUser(userEmail, userPassword).enqueue(object : Callback<DataClass?> {
                override fun onResponse(call: Call<DataClass?>, response: Response<DataClass?>) {
                    progressBar.visibility = View.INVISIBLE
                    val responseBody = response.body()
                    if(responseBody?.token.toString() != "null") {
                        Toast.makeText(
                            activity,
                            "You've been logged in",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else {
                        Toast.makeText(
                            activity,
                            "Wrong Credentials!!\n\nPlease check your email/password and try again!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    }

                override fun onFailure(call: Call<DataClass?>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(
                    activity,
                    "Wrong Credentials!!\n\nPlease check your email/password and try again!",
                    Toast.LENGTH_LONG
                ).show()
            }
            })

    }

        return v
    }
}