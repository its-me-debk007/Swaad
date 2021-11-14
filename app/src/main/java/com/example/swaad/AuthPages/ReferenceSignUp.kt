package com.example.swaad.AuthPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.swaad.ApiRequests.*
import com.example.swaad.R
import com.example.swaad.ProfilePages.TermsAndConditions
import com.example.swaad.otp_sign_up
import com.google.android.material.textfield.TextInputEditText

class ReferenceSignUp : Fragment() {

    private fun isValidEmail(str: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }
    companion object{
        lateinit var nextPage: String
        lateinit var emailSignUp:String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_reference_sign_up, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBar2)
        val termsConditions: TextView = v.findViewById(R.id.textView5)
        termsConditions.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, TermsAndConditions())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
//        val unsee=v.findViewById<ImageView>(R.id.unsee_password)
//        var flag =0

//        val password=v.findViewById<TextInputEditText>(R.id.editTextTextPersonName3v2)

//        unsee.setOnClickListener {
//            if(flag%2==0) {
//                password.transformationMethod =HideReturnsTransformationMethod.getInstance()
//                password.setSelection(password.getText().length);
//                flag++
//            }
//            else
//            {
//                password.transformationMethod = PasswordTransformationMethod.getInstance()
//                password.setSelection(password.getText().length);
//                flag++
//            }
////            password.setTextCursorDrawable(password.getText().length)
//        }
        val logIn: TextView = v.findViewById(R.id.textView7)
        logIn.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        val sign_up = v.findViewById<Button>(R.id.sign_up_button)
        sign_up.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            sign_up.isEnabled = false
            sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background2))

            val name = v.findViewById<EditText>(R.id.editTextTextPersonName).text.toString().trim()
           emailSignUp =
                v.findViewById<EditText>(R.id.signUpEmail).text.toString().trim()
            val password =v.findViewById<TextInputEditText>(R.id.Sign_up_password).text.toString().trim()
            if(name.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<EditText>(R.id.editTextTextPersonName).error="Username can not be empty"
                sign_up.isEnabled = true
                sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
            if(!isValidEmail(emailSignUpl)){
                progressBar.visibility=View.INVISIBLE
                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar2)
                v.findViewById<EditText>(R.id.signUpEmail).error="Please enter a valid email "
                sign_up.isEnabled = true
                sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
            else if(password.length < 5)
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<TextInputEditText>(R.id.Sign_up_password).error="Minimum length of password should be 5 characters"
                sign_up.isEnabled = true
                sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
    val jsonConverterSignUp=JsonConverterSignUP(emailSignUp,password,name)
            Toast.makeText(activity,"Please wait !", Toast.LENGTH_LONG).show()
            RetrofitClient.init().createUser(jsonConverterSignUp).enqueue(object : Callback<DataClassSignUp?>{
                    override fun onResponse(
                        call: Call<DataClassSignUp?>,
                        response: Response<DataClassSignUp?>
                    ) {
                        progressBar.visibility=View.INVISIBLE
                        val status = response.body()?.status.toString()
//                        Toast.makeText(activity, status, Toast.LENGTH_LONG).show()
                        if(status == "User registered successfully and an OTP has been sent to your email.") {
//                            Toast.makeText(activity, status, Toast.LENGTH_LONG).show()
                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.fragment_container, otp_sign_up())
                            fragmentTransaction?.addToBackStack(null)
                            fragmentTransaction?.commit()
                        }
                        else{
                            Toast.makeText(activity, status, Toast.LENGTH_LONG).show()
                            v.findViewById<TextInputEditText>(R.id.Sign_up_password).text?.clear()

                            sign_up.isEnabled = true
                            sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                        }
                    }

                    override fun onFailure(call: Call<DataClassSignUp?>, t: Throwable) {
                        progressBar.visibility=View.INVISIBLE
                        Toast.makeText(
                            activity, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                        v.findViewById<TextInputEditText>(R.id.Sign_up_password).text?.clear()
                        sign_up.isEnabled = true
                        sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                    }
                })
        }
        return v

    }
}