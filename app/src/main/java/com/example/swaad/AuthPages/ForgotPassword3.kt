package com.example.swaad.AuthPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.swaad.ApiRequests.DataClassSignUp
import com.example.swaad.ApiRequests.JsonConverterConfirmPassword
import com.example.swaad.R
import com.example.swaad.ApiRequests.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
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
            resetButton.isEnabled = false
            resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background2))

            val newPassword: TextInputEditText = v.findViewById(R.id.password1)
            val newPasswordText=newPassword.text.toString().trim()

            val newConfirmPassword: TextInputEditText = v.findViewById(R.id.password2)
            val newConfirmPasswordText =  newConfirmPassword.text.toString().trim()
            if(newPasswordText!=newConfirmPasswordText)
            {
                newPassword.error="Please check both the password you have typed"
            }
//            val tokenString = "Token " + tokenValue
            Toast.makeText(activity,ForgotPassword1.email, Toast.LENGTH_LONG).show()
            val jsonConverterConfirmPassword=JsonConverterConfirmPassword(newConfirmPasswordText,ForgotPassword1.email)
//            RetrofitClient.init().setNewPassword(jsonConverterConfirmPassword).enqueue(object : Callback<DataClassSignUp?> {
//                override fun onResponse(call: Call<DataClassSignUp?>, response: Response<DataClassSignUp?>) {
////                    Toast.makeText(activity,response.code(),Toast.LENGTH_LONG).show()
//                    if(response.body()?.status=="New Password Set"){
//                        progressBar.visibility=View.INVISIBLE
//                        Toast.makeText(activity,"New Password has been set", Toast.LENGTH_LONG).show()
//                        val fragmentManager = activity?.supportFragmentManager
//                        val fragmentTransaction = fragmentManager?.beginTransaction()
//                        fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
////                        fragmentTransaction?.addToBackStack(null)
//                        fragmentTransaction?.commit()
//                    }
//                    else{
//                        Toast.makeText(activity,response.code(),Toast.LENGTH_LONG).show()
//                        progressBar.visibility=View.INVISIBLE
//                        Toast.makeText(activity,"New Password has not been set",
//                            Toast.LENGTH_LONG).show()
//                        resetButton.isEnabled = true
//                        resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
//                        newPassword.text?.clear()
//                        newConfirmPassword.text?.clear()
//                    }
//                }


//                override fun onFailure(call: Call<DataClassSignUp?>, t: Throwable) {
//                    progressBar.visibility=View.INVISIBLE
//                    Toast.makeText(activity,"New Password has not been set",
//                        Toast.LENGTH_LONG).show()
//
//                    resetButton.isEnabled = true
//                    resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
//                    newPassword.text?.clear()
//                    newConfirmPassword.text?.clear()
//                }
//            })
            RetrofitClient.init().setNewPassword(jsonConverterConfirmPassword).enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if(response.isSuccessful){
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(activity,"New Password has been set", Toast.LENGTH_LONG).show()
                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
//                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    }
                    else{
//                        Toast.makeText(activity,response.body()?.status.toString(),Toast.LENGTH_LONG).show()
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(activity,"New Password has not been set",
                            Toast.LENGTH_LONG).show()
                        resetButton.isEnabled = true
                        resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                        newPassword.text?.clear()
                        newConfirmPassword.text?.clear()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,"New Password has not been set",
                        Toast.LENGTH_LONG).show()

                    resetButton.isEnabled = true
                    resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))

                }
            })


        }

        return v
    }

}