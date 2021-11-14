package com.example.swaad.AuthPages

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
import com.example.swaad.AuthPages.ForgotPassword1.Companion.email
import com.example.swaad.R
import com.example.swaad.AuthPages.ReferenceSignUp.Companion.nextPage
import com.example.swaad.ApiRequests.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPassword2 : Fragment() {


    companion object {
        lateinit var tokenValue: String
    }
    lateinit var timerCountDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_forgot_password_2, container, false)
        val progressBar = v.findViewById<ProgressBar>(R.id.progressBar4)
        val verifyBtn: Button = v.findViewById(R.id.verify_button)

//        var timer = 30
        timerCountDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000

//                timer--
                v.findViewById<TextView>(R.id.timer).text = "00:" + timeLeft.toString()
            }

            override fun onFinish() {
                v.findViewById<TextView>(R.id.timer).text = "00:00"
            }

        }.start()




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

//            v.findViewById<TextView>(R.id.textView2).text = userEmail
            if(v.findViewById<EditText>(R.id.otp1).text.toString().trim().isEmpty() || v.findViewById<EditText>(R.id.otp2).text.toString().trim().isEmpty() || v.findViewById<EditText>(R.id.otp3).text.toString().trim().isEmpty() || v.findViewById<EditText>(R.id.otp4).text.toString().trim().isEmpty()){
                progressBar.visibility=View.INVISIBLE
//                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar4)
                v.findViewById<EditText>(R.id.otp4).error = "Please enter a valid otp"
                verifyBtn.isEnabled = true
                verifyBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
            val userOtp = v.findViewById<EditText>(R.id.otp1).text.toString().trim() + v.findViewById<EditText>(R.id.otp2).text.toString().trim() + v.findViewById<EditText>(R.id.otp3).text.toString().trim() + v.findViewById<EditText>(R.id.otp4).text.toString().trim()
//            Toast.makeText(activity, "Button clicked", Toast.LENGTH_LONG).show()

            RetrofitClient.init().verifyOtp(userEmail, userOtp)
                .enqueue(object : Callback<DataVerifyOtpClass?> {
                    override fun onResponse(
                        call: Call<DataVerifyOtpClass?>,
                        response: Response<DataVerifyOtpClass?>
                    ) {

                        val responseBody = response.body()
                        try {

                            if (responseBody!!.status == "OTP verified You can now change your password") {
                                progressBar.visibility = View.INVISIBLE
                                Toast.makeText(activity, "OTP verified", Toast.LENGTH_LONG).show()

                                tokenValue = responseBody.token

                                timerCountDownTimer.cancel()
                                val fragmentManager = activity?.supportFragmentManager
                                val fragmentTransaction = fragmentManager?.beginTransaction()
                                fragmentTransaction?.replace(R.id.fragment_container, ForgotPassword3())
                                fragmentTransaction?.addToBackStack(null)
                                fragmentTransaction?.commit()

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

                        override fun onFailure(call: Call<DataVerifyOtpClass?>, t: Throwable) {
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
        return v
    }

}


//    private fun startTimer() {
//        val countDownTimer = CountDownTimer(30000, 1000)
//    }

