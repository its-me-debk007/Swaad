package com.example.swaad.AuthPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.swaad.ApiRequests.DataSetNewPasswordClass
import com.example.swaad.AuthPages.ForgotPassword2.Companion.tokenValue
import com.example.swaad.R
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.ForgotPassword1.Companion.email
import com.google.android.material.textfield.TextInputEditText
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

            val newPassword: TextInputEditText = v.findViewById(R.id.editTextTextPersonName7v2)
            val newPasswordText = newPassword.text.toString().trim()

            val newConfirmPassword: TextInputEditText = v.findViewById(R.id.editTextTextPersonName8v2)
            val newConfirmPasswordText =  newConfirmPassword.text.toString().trim()

            if(newPasswordText.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                newPassword.error="This section cannot be empty"
                resetButton.isEnabled = true
                resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
            var flagLower = false
            var flagUpper = false
            var flagNumber = false
            for(i in 0..newPasswordText.length - 1){
                if(newPasswordText[i] in 'a'..'z')
                    flagLower = true
                if(newPasswordText[i] in 'A'..'Z')
                    flagUpper = true
                if(newPasswordText[i] in '0'..'9')
                    flagNumber = true
            }
            if(!(flagLower && flagUpper && flagNumber) || newPasswordText.length < 5){
                progressBar.visibility=View.INVISIBLE
                newPassword.error="Minimum length of password should be 5 characters\n" +
                        "\n" +
                        "There should be atleast one uppercase, lowercase and a numeric digit"
                resetButton.isEnabled = true
                resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
            if(newConfirmPasswordText.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                newConfirmPassword.error="This section cannot be empty"
                resetButton.isEnabled = true
                resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
            if(newPasswordText != newConfirmPasswordText){
                progressBar.visibility=View.INVISIBLE
                newConfirmPassword.error="The passwords do not match"
                resetButton.isEnabled = true
                resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }

//            val tokenString = "Token " + tokenValue

            Toast.makeText(activity,"Setting new Password", Toast.LENGTH_LONG).show()

            RetrofitClient.init().setNewPassword(newConfirmPasswordText, email).enqueue(object :
                Callback<DataSetNewPasswordClass?> {
                override fun onResponse(call: Call<DataSetNewPasswordClass?>, response: Response<DataSetNewPasswordClass?>) {

                    val responseBody = response.body()

                    progressBar.visibility = View.INVISIBLE
                    if (response.code() == 200) {
                        Toast.makeText(activity, "New Password has been set", Toast.LENGTH_LONG)
                            .show()

                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(
                            activity, "Ouch!",
                            Toast.LENGTH_LONG
                        ).show()

                        resetButton.isEnabled = true
                        resetButton.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.button_background
                            )
                        )
                        newPassword.text?.clear()
                        newConfirmPassword.text?.clear()

                    }
                }
                override fun onFailure(call: Call<DataSetNewPasswordClass?>, t: Throwable) {
                    progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,"API has crashed!\n\nPlease try again",
                        Toast.LENGTH_LONG).show()

                    resetButton.isEnabled = true
                    resetButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                    newPassword.text?.clear()
                    newConfirmPassword.text?.clear()
                }
            })


        }

        return v
    }

}