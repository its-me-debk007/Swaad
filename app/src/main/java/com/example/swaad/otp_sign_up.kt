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
        verify.setOnClickListener {
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
                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    }
                    else if (response.code() == 400) {
                        Toast.makeText(activity, "OTP incorrect", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(activity, "OTP expired", Toast.LENGTH_LONG).show()
                    }

                }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(activity, "Crashed", Toast.LENGTH_LONG).show()
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
            val jsonConverter=JsonConverter(ReferenceSignUp.emailSignUp)
            RetrofitClient.init().resendOtpSignUp(jsonConverter).enqueue(object : Callback<List<ResponseBody>?> {
                override fun onResponse(
                    call: Call<List<ResponseBody>?>,
                    response: Response<List<ResponseBody>?>
                ) {
                    if(response.isSuccessful) {
                        verify.setOnClickListener {
                            val userOtp = v.findViewById<EditText>(R.id.OtpSignUp1).text.toString()
                                .trim() + v.findViewById<EditText>(R.id.OtpSignUp2).text.toString()
                                .trim() + v.findViewById<EditText>(R.id.OtpSignUp3).text.toString()
                                .trim() + v.findViewById<EditText>(R.id.OtpSignUp4).text.toString()
                                .trim()
                            val JsonConverterSignUpOtp =
                                JsonConverterSignUpOtp(ReferenceSignUp.emailSignUp, userOtp)
//            RetrofitClient.init().getSignUpOtp(JsonConverterSignUpOtp)
//                .enqueue(object : Callback<ResponseBody?> {
//                    override fun onResponse(
//                        call: Call<ResponseBody?>,
//                        response: Response<ResponseBody?>
//                    )
////                    {
                            RetrofitClient.init().getSignUpOtp(JsonConverterSignUpOtp)
                                .enqueue(object : Callback<ResponseBody?> {
                                    override fun onResponse(
                                        call: Call<ResponseBody?>,
                                        response: Response<ResponseBody?>
                                    ) {
                                        if (response.isSuccessful) {
                                            val fragmentManager = activity?.supportFragmentManager
                                            val fragmentTransaction =
                                                fragmentManager?.beginTransaction()
                                            fragmentTransaction?.replace(
                                                R.id.fragment_container,
                                                FragmentLogIn()
                                            )
                                            fragmentTransaction?.addToBackStack(null)
                                            fragmentTransaction?.commit()
                                        } else if (response.code() == 400) {
                                            Toast.makeText(
                                                activity,
                                                "OTP incorrect",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                activity,
                                                "OTP expired",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }

                                    override fun onFailure(
                                        call: Call<ResponseBody?>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(activity, "Crashed", Toast.LENGTH_LONG)
                                            .show()
                                    }
                                })

                        }
                    }
                    else if (response.code() == 400) {
                        Toast.makeText(activity, "OTP incorrect", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(activity, "OTP expired", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseBody>?>, t: Throwable) {
                    Toast.makeText(activity, "Crashed", Toast.LENGTH_LONG).show()
                }
            })
        }
        return v
    }


}