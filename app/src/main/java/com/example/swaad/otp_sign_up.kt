package com.example.swaad

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.swaad.ApiRequests.*
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.AuthPages.ReferenceSignUp
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class otp_sign_up : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_otp_sign_up, container, false)
        var resend_now=v.findViewById<TextView>(R.id.resendSignupOtp)
        val verify = v.findViewById<Button>(R.id.verify_button_sign_up)
        val otp1=v.findViewById<TextView>(R.id.OtpSignUp1)
        val otp2=v.findViewById<TextView>(R.id.OtpSignUp2)
        val otp3=v.findViewById<TextView>(R.id.OtpSignUp3)
        val otp4=v.findViewById<TextView>(R.id.OtpSignUp4)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBarOtpSignup)
        otp1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (otp1.text.toString().length == 1) {
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
                if (otp2.text.toString().length == 1) {
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
                if (otp3.text.toString().length == 1) {
                    otp4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        verify.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            val userOtp = v.findViewById<EditText>(R.id.OtpSignUp1).text.toString()
                .trim() + v.findViewById<EditText>(R.id.OtpSignUp2).text.toString().trim() + v.findViewById<EditText>(R.id.OtpSignUp3).text.toString()
                .trim() + v.findViewById<EditText>(R.id.OtpSignUp4).text.toString().trim()
            val JsonConverterSignUpOtp = JsonConverterSignUpOtp(ReferenceSignUp.emailSignUp,userOtp)
//            RetrofitClient.init().getSignUpOtp(JsonConverterSignUpOtp)
//                .enqueue(object : Callback<ResponseBody?> {
//                    override fun onResponse(
//                        call: Call<ResponseBody?>,
//                        response: Response<ResponseBody?>
//                    )
////                    {
            RetrofitClient.init().getSignUpOtp(JsonConverterSignUpOtp).enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if(response.isSuccessful){
                        progressBar.visibility=View.INVISIBLE
                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    }
                    else if (response.code() == 400) {
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(activity, "OTP incorrect", Toast.LENGTH_LONG).show()
                    } else {
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(activity, "OTP expired", Toast.LENGTH_LONG).show()
                    }

                }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity, "Crashed  Api", Toast.LENGTH_LONG).show()
                }
            })
//                        val response = response.body()?.status.toString()
//                        if (response == "OTP verified, proceed to login.") {
//                            val fragmentManager = activity?.supportFragmentManager
//                            val fragmentTransaction = fragmentManager?.beginTransaction()
//                            fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
//                            fragmentTransaction?.addToBackStack(null)
//                            fragmentTransaction?.commit()
//                        } else {
//                            Toast.makeText(activity, response, Toast.LENGTH_LONG).show()
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<DataVerifyOtpClass?>, t: Throwable) {
//                        Toast.makeText(activity, "Crashed", Toast.LENGTH_LONG).show()
//                    }
//                })
        }
        resend_now.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            val jsonConverterOtp=JsonConverter(ReferenceSignUp.emailSignUp)
            RetrofitClient.init().resendOtpSignUp(jsonConverterOtp).enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if(response.isSuccessful) {
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(
                            activity,
                            "Otp has been resent successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,"Crashed Api", Toast.LENGTH_LONG).show()
                }
            })
        }
        return v
    }


}