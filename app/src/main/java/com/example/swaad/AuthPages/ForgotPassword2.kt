package com.example.swaad.AuthPages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.swaad.ApiRequests.DataVerifyOtpClass
import com.example.swaad.ApiRequests.JsonConverter
import com.example.swaad.ApiRequests.JsonConverterVerifyOtp
import com.example.swaad.AuthPages.ForgotPassword1.Companion.email
import com.example.swaad.R
import com.example.swaad.AuthPages.ReferenceSignUp.Companion.nextPage
import com.example.swaad.ApiRequests.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPassword2 : Fragment() {
    companion object {
//        lateinit var tokenValue: String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_forgot_password_2, container, false)
        val progressBar = v.findViewById<ProgressBar>(R.id.progressBar4)
        val verifyBtn: Button = v.findViewById(R.id.verify_button)
        val otp1 = v.findViewById<EditText>(R.id.otp1)
        val otp2 = v.findViewById<EditText>(R.id.Otp2)
        val otp3 = v.findViewById<EditText>(R.id.Otp3)
        val otp4 = v.findViewById<EditText>(R.id.Otp4)
        Toast.makeText(activity, nextPage, Toast.LENGTH_LONG).show()
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

        verifyBtn.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            verifyBtn.isEnabled = false
            verifyBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_background2
                )
            )

            val userEmail = email
            val userOtp = v.findViewById<EditText>(R.id.otp1).text.toString()
                .trim() + v.findViewById<EditText>(R.id.Otp2).text.toString().trim() + v.findViewById<EditText>(R.id.Otp3).text.toString()
                .trim() + v.findViewById<EditText>(R.id.Otp4).text.toString().trim()
            val jsonConverterVerifyOtp=JsonConverterVerifyOtp(userEmail,userOtp)
                RetrofitClient.init().verifyOtp(jsonConverterVerifyOtp)
                    .enqueue(object : Callback<ResponseBody?> {
                        override fun onResponse(
                            call: Call<ResponseBody?>,
                            response: Response<ResponseBody?>
                        ) {
                            try {
                                if (response.isSuccessful) {
                                    progressBar.visibility = View.INVISIBLE
                                    Toast.makeText(activity, "Create Your New Password", Toast.LENGTH_LONG).show()
                                    val fragmentManager = activity?.supportFragmentManager
                                    val fragmentTransaction =
                                        fragmentManager?.beginTransaction()
                                    fragmentTransaction?.replace(
                                        R.id.fragment_container,
                                        ForgotPassword3()
                                    )
//                                    fragmentTransaction?.addToBackStack(null)
                                    fragmentTransaction?.commit()
                                }
                                else if(response.code()==400)
                                {
                                    progressBar.visibility = View.INVISIBLE
                                    Toast.makeText(activity, "Please Enter an email adress", Toast.LENGTH_LONG).show()
                                }
                                else if(response.code()==408)
                                {
                                    progressBar.visibility = View.INVISIBLE
                                    Toast.makeText(activity, "Sorry, entered otp has expired", Toast.LENGTH_LONG).show()
                                }
                                else if(response.code()==409)
                                {
                                    progressBar.visibility = View.INVISIBLE
                                    Toast.makeText(activity, "Entered Otp doesn't match the send otp", Toast.LENGTH_LONG).show()
                                }
                                else if(response.code()==401)
                                {
                                    progressBar.visibility = View.INVISIBLE
                                    Toast.makeText(activity, "Entered otp doesn't belong to your email", Toast.LENGTH_LONG).show()
                                }


                            } catch (e: Exception) {
                                progressBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    activity,
                                    "OTP Not Verified!\n\nPlease try again",
                                    Toast.LENGTH_LONG
                                ).show()
                                v.findViewById<EditText>(R.id.otp1).text.clear()
                                v.findViewById<EditText>(R.id.Otp2).text.clear()
                                v.findViewById<EditText>(R.id.Otp3).text.clear()
                                v.findViewById<EditText>(R.id.Otp4).text.clear()

                                verifyBtn.isEnabled = true
                                verifyBtn.setBackgroundColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.button_background
                                    )
                                )
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                            progressBar.visibility = View.INVISIBLE
                            Toast.makeText(
                                activity, "OTP Not Verified!\n" +
                                        "\n" +
                                        "Please try again", Toast.LENGTH_LONG
                            ).show()
                            v.findViewById<EditText>(R.id.otp1).text.clear()
                            v.findViewById<EditText>(R.id.Otp2).text.clear()
                            v.findViewById<EditText>(R.id.Otp3).text.clear()
                            v.findViewById<EditText>(R.id.Otp4).text.clear()

                            verifyBtn.isEnabled = true
                            verifyBtn.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.button_background
                                )
                            )
                        }
                    })

            }
        val resend_now=v.findViewById<TextView>(R.id.resendOtp)
        resend_now.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            val jsonConverterOtp= JsonConverter(FragmentLogIn.userEmail)
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


//    private fun startTimer() {
//        val countDownTimer = CountDownTimer(30000, 1000)
//    }

