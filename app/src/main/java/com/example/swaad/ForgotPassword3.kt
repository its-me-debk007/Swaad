package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.swaad.ForgotPassword2.Companion.tokenValue
import com.google.android.material.textfield.TextInputEditText
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
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBar5)
        val resetButton : Button = v.findViewById(R.id.button3)
        resetButton.setOnClickListener{
            progressBar.visibility=View.VISIBLE
            val newPassword: TextInputEditText = v.findViewById(R.id.editTextTextPersonName7v2)
            newPassword.text.toString().trim()

            val newConfirmPassword: TextInputEditText = v.findViewById(R.id.editTextTextPersonName8v2)
            val newConfirmPasswordText =  newConfirmPassword.text.toString().trim()

            val tokenString = "Token " + tokenValue

            Toast.makeText(activity,"Setting new Password", Toast.LENGTH_LONG).show()

            RetrofitClient.init().setNewPassword(newConfirmPasswordText, tokenString).enqueue(object :
                Callback<DataSetNewPasswordClass?> {
                override fun onResponse(call: Call<DataSetNewPasswordClass?>, response: Response<DataSetNewPasswordClass?>) {

                    val responseBody = response.body()
                    try {
                        progressBar.visibility=View.INVISIBLE
                        responseBody!!.name
                        Toast.makeText(activity,"New Password has been set", Toast.LENGTH_LONG).show()

                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    }
                    catch(e: Exception){
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(activity,"New Password has not been set",
                            Toast.LENGTH_LONG).show()
                        newPassword.text?.clear()
                        newConfirmPassword.text?.clear()
                    }
                }
                override fun onFailure(call: Call<DataSetNewPasswordClass?>, t: Throwable) {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,"New Password has not been set",
                        Toast.LENGTH_LONG).show()
                    newPassword.text?.clear()
                    newConfirmPassword.text?.clear()
                }
            })


        }

        return v
    }

}