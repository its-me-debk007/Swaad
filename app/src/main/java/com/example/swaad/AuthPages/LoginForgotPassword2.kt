package com.example.swaad.AuthPages

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.swaad.ApiRequests.DataVerifyOtpClass
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.ForgotPassword2.Companion.tokenValue
import com.example.swaad.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginForgotPassword2: Fragment() {

    lateinit var timerCountDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.login_forgot_password_2, container, false)
        val progressBar = v.findViewById<ProgressBar>(R.id.progressingBar)
        val verifyBtn: Button = v.findViewById(R.id.verifyBtn)

        timerCountDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000

                v.findViewById<TextView>(R.id.timer2).text = "00:" + timeLeft.toString()
            }

            override fun onFinish() {
                v.findViewById<TextView>(R.id.timer2).text = "00:00"
            }

        }.start()

        val otp1 = v.findViewById<EditText>(R.id.otpBox1)
        val otp2 = v.findViewById<EditText>(R.id.otpBox2)
        val otp3 = v.findViewById<EditText>(R.id.otpBox3)
        val otp4 = v.findViewById<EditText>(R.id.otpBox4)

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

            val userEmail = ForgotPassword1.email
            val userOtp = otp1.text.toString().trim() + otp2.text.toString().trim() + otp3.text.toString().trim() + otp4.text.toString().trim()

            if(otp1.text.toString().trim().isEmpty() || otp2.text.toString().trim().isEmpty() || otp3.text.toString().trim().isEmpty() || otp4.text.toString().trim().isEmpty()){
                progressBar.visibility=View.INVISIBLE
//                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar4)
                otp4.error = "Please enter a valid otp"
                verifyBtn.isEnabled = true
                verifyBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }



            RetrofitClient.init().verifyOtp(userEmail, userOtp)
                .enqueue(object : Callback<DataVerifyOtpClass?> {
                    override fun onResponse(
                        call: Call<DataVerifyOtpClass?>,
                        response: Response<DataVerifyOtpClass?>
                    ) {

                        val responseBody = response.body()
                        if (responseBody!!.status == "OTP verified You can now change your password") {
                                progressBar.visibility = View.INVISIBLE
                                Toast.makeText(activity, responseBody.status, Toast.LENGTH_LONG).show()

                                tokenValue = responseBody.token

                                timerCountDownTimer.cancel()
                                val fragmentManager = activity?.supportFragmentManager
                                val fragmentTransaction = fragmentManager?.beginTransaction()
                                fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                                fragmentTransaction?.addToBackStack(null)
                                fragmentTransaction?.commit()

                                }
                            else{
                                progressBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    activity,
                                    "OTP Not Verified!\n\nPlease try again",
                                    Toast.LENGTH_LONG
                                ).show()
                                v.findViewById<EditText>(R.id.otpBox1).text.clear()
                                v.findViewById<EditText>(R.id.otpBox2).text.clear()
                                v.findViewById<EditText>(R.id.otpBox3).text.clear()
                                v.findViewById<EditText>(R.id.otpBox4).text.clear()

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
                            v.findViewById<EditText>(R.id.otpBox1).text.clear()
                            v.findViewById<EditText>(R.id.otpBox2).text.clear()
                            v.findViewById<EditText>(R.id.otpBox3).text.clear()
                            v.findViewById<EditText>(R.id.otpBox4).text.clear()

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