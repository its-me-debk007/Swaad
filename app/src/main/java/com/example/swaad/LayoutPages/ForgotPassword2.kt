package com.example.swaad.LayoutPages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.swaad.ApiRequest.DataVerifyOtpClass
import com.example.swaad.LayoutPages.ForgotPassword1.Companion.email
import com.example.swaad.R
import com.example.swaad.LayoutPages.ReferenceSignUp.Companion.nextPage
import com.example.swaad.ApiRequest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPassword2 : Fragment() {
    companion object{
        lateinit var tokenValue:String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v= inflater.inflate(R.layout.fragment_forgot_password_2, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBar4)
        val verifyBtn : Button = v.findViewById(R.id.verify_button)
        val otp1=v.findViewById<EditText>(R.id.otp1)
        val otp2=v.findViewById<EditText>(R.id.otp2)
        val otp3=v.findViewById<EditText>(R.id.otp3)
        val otp4=v.findViewById<EditText>(R.id.otp4)
        otp1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(otp1.text.toString().length==1) {
                    otp2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        otp2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(otp2.text.toString().length==1) {
                    otp3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        otp3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(otp3.text.toString().length==1) {
                    otp4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        verifyBtn.setOnClickListener{
            progressBar.visibility=View.VISIBLE
            Toast.makeText(activity,"Verifying OTP",Toast.LENGTH_LONG).show()
            val userEmail = email
//            v.findViewById<TextView>(R.id.textView2).text = userEmail

            val userOtp = v.findViewById<EditText>(R.id.otp1).text.toString().trim() + v.findViewById<EditText>(
                R.id.otp2
            ).text.toString().trim() + v.findViewById<EditText>(R.id.otp3).text.toString().trim() + v.findViewById<EditText>(
                R.id.otp4
            ).text.toString().trim()

            RetrofitClient.init()
                .verifyOtp(userEmail, userOtp).enqueue(object : Callback<DataVerifyOtpClass?> {
                override fun onResponse(call: Call<DataVerifyOtpClass?>, response: Response<DataVerifyOtpClass?>) {

                    val responseBody = response.body()
                    try {

                        if(responseBody!!.status == "OTP verified You can now change your password") {
                            progressBar.visibility=View.INVISIBLE
                            Toast.makeText(activity, "OTP verified", Toast.LENGTH_LONG).show()

                            tokenValue = responseBody.token

                            if(nextPage == "forgotPassword") {
                                val fragmentManager = activity?.supportFragmentManager
                                val fragmentTransaction = fragmentManager?.beginTransaction()
                                fragmentTransaction?.replace(
                                    R.id.fragment_container,
                                    ForgotPassword3()
                                )
                                fragmentTransaction?.addToBackStack(null)
                                fragmentTransaction?.commit()
                            }

                            else if(nextPage == "signUp") {
                                val fragmentManager = activity?.supportFragmentManager
                                val fragmentTransaction = fragmentManager?.beginTransaction()
                                fragmentTransaction?.replace(
                                    R.id.fragment_container,
                                    FragmentLogIn()
                                )
                                fragmentTransaction?.addToBackStack(null)
                                fragmentTransaction?.commit()
                            }
                        }
                    }
                    catch(e: Exception){
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(activity,"Not",Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<DataVerifyOtpClass?>, t: Throwable) {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,"Not",Toast.LENGTH_LONG).show()
                }
            })

        }
        return v
    }

//    private fun startTimer() {
//        val countDownTimer = CountDownTimer(30000, 1000)
//    }
}
