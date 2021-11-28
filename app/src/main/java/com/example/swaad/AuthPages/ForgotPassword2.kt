package com.example.swaad.AuthPages

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
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
        lateinit var tokenValue: String
    }

    lateinit var timerCountDownTimer: CountDownTimer
    var timerOnStatus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_forgot_password_2, container, false)
        val progressBar = v.findViewById<ProgressBar>(R.id.progressBar4)
        val verifyBtn: Button = v.findViewById(R.id.verify_button)
        var resend_now=v.findViewById<TextView>(R.id.resendOtp)


        fun startTimer() {
            timerCountDownTimer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeLeft = millisUntilFinished / 1000
                    if (timeLeft.toString().length == 2)
                        v.findViewById<TextView>(R.id.timer).text = "00:" + timeLeft.toString()
                    else
                        v.findViewById<TextView>(R.id.timer).text = "00:0" + timeLeft.toString()

                    resend_now.setTextColor(Color.DKGRAY)
                }

                override fun onFinish() {
                    v.findViewById<TextView>(R.id.timer).text = "00:00"
                    timerOnStatus = false
                    resend_now.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                }

            }.start()
        }
        startTimer()

        val otp1 = v.findViewById<EditText>(R.id.Otp1)
        val otp2 = v.findViewById<EditText>(R.id.Otp2)
        val otp3 = v.findViewById<EditText>(R.id.Otp3)
        val otp4 = v.findViewById<EditText>(R.id.Otp4)

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
            val userOtp =
                otp1.text.toString().trim() + otp2.text.toString().trim() + otp3.text.toString()
                    .trim() + otp4.text.toString().trim()

            if (otp1.text.toString().trim().isEmpty() || otp2.text.toString().trim()
                    .isEmpty() || otp3.text.toString().trim().isEmpty() || otp4.text.toString()
                    .trim().isEmpty()
            ) {
                progressBar.visibility = View.INVISIBLE
//                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar4)
                otp4.error = "Please enter a valid otp"
                verifyBtn.isEnabled = true
                verifyBtn.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_background
                    )
                )
                return@setOnClickListener
            }
//            val userOtp2 = v.findViewById<EditText>(R.id.otp1).text.toString().trim() + v.findViewById<EditText>(R.id.otp2).text.toString().trim() + v.findViewById<EditText>(R.id.otp3).text.toString().trim() + v.findViewById<EditText>(R.id.otp4).text.toString().trim()
//            Toast.makeText(activity, "Button clicked", Toast.LENGTH_LONG).show()

            RetrofitClient.init().verifyOtp(userEmail, userOtp)
                .enqueue(object : Callback<DataVerifyOtpClass?> {
                    override fun onResponse(
                        call: Call<DataVerifyOtpClass?>,
                        response: Response<DataVerifyOtpClass?>
                    ) {

                        val responseBody = response.body()


                        if (response.code() == 200) {
                            progressBar.visibility = View.INVISIBLE
                            Toast.makeText(activity, "OTP has been verified", Toast.LENGTH_LONG)
                                .show()

                            timerCountDownTimer.cancel()
                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.fragment_container, ForgotPassword3())
                            fragmentTransaction?.addToBackStack("tag")
                            fragmentTransaction?.commit()

                        } else if(response.code() == 408){
                            progressBar.visibility = View.INVISIBLE
                            Toast.makeText(
                                activity,
                                "OTP has expired!\n\nPlease tap on 'Resend Now'",
                                Toast.LENGTH_LONG
                            ).show()
                            v.findViewById<EditText>(R.id.Otp1).text.clear()
                            v.findViewById<EditText>(R.id.Otp2).text.clear()
                            v.findViewById<EditText>(R.id.Otp3).text.clear()
                            v.findViewById<EditText>(R.id.Otp4).text.clear()
                            otp1.requestFocus()

                            verifyBtn.isEnabled = true
                            verifyBtn.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.button_background
                                )
                            )
                        }
                        else if(response.code() == 409){
                            progressBar.visibility = View.INVISIBLE
                            Toast.makeText(
                                activity,
                                "Incorrect OTP",
                                Toast.LENGTH_LONG
                            ).show()
                            v.findViewById<EditText>(R.id.Otp1).text.clear()
                            v.findViewById<EditText>(R.id.Otp2).text.clear()
                            v.findViewById<EditText>(R.id.Otp3).text.clear()
                            v.findViewById<EditText>(R.id.Otp4).text.clear()
                            otp1.requestFocus()

                            verifyBtn.isEnabled = true
                            verifyBtn.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.button_background
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<DataVerifyOtpClass?>, t: Throwable) {
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(
                            activity, "OTP Not Verified!\n" +
                                    "\n" +
                                    "Please try again", Toast.LENGTH_LONG
                        ).show()
                        v.findViewById<EditText>(R.id.Otp1).text.clear()
                        v.findViewById<EditText>(R.id.Otp2).text.clear()
                        v.findViewById<EditText>(R.id.Otp3).text.clear()
                        v.findViewById<EditText>(R.id.Otp4).text.clear()
                        otp1.requestFocus()

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
        resend_now.setOnClickListener {
            if (!timerOnStatus) {
                progressBar.visibility = View.VISIBLE
                timerOnStatus = true

                val jsonConverterOtp= JsonConverter(email)
                RetrofitClient.init().getOtp(jsonConverterOtp).enqueue(object : Callback<ResponseBody?> {

                        override fun onResponse(
                            call: Call<ResponseBody?>,
                            response: Response<ResponseBody?>
                        ) {
                            if(response.code() == 200) {

                                progressBar.visibility=View.INVISIBLE
                                Toast.makeText(activity,"Otp has been resent successfully", Toast.LENGTH_LONG).show()
                                startTimer()
                            }
                            else
                            {
                                progressBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    activity,
                                    "Otp has not been resent\n\nKindly try again",
                                    Toast.LENGTH_LONG
                                ).show()
                                timerOnStatus = false
                            }

                        }

                        override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                            progressBar.visibility = View.INVISIBLE
                            Toast.makeText(activity, "Crashed Api", Toast.LENGTH_LONG).show()
                            timerOnStatus = false
                        }
                })
            }
        }

        return v
    }
}

