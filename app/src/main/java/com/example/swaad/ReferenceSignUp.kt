package com.example.swaad

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import android.view.MotionEvent

import android.view.View.OnTouchListener
import android.widget.EditText








class ReferenceSignUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_reference_sign_up, container, false)
        val termsConditions: TextView = v.findViewById(R.id.textView5)
        termsConditions.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, TermsAndConditions())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
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
            val name = v.findViewById<TextView>(R.id.editTextTextPersonName).text.toString().trim()
            val email =
                v.findViewById<TextView>(R.id.editTextTextPersonName2).text.toString().trim()
            val password =
                v.findViewById<TextView>(R.id.editTextTextPersonName3).text.toString().trim()

            if(name.isEmpty())
            {
                v.findViewById<TextView>(R.id.editTextTextPersonName).error="Username can not be empty"
                return@setOnClickListener
            }
            else if(email.isEmpty())
            {
                v.findViewById<TextView>(R.id.editTextTextPersonName2).error="Email can not be empty"
                return@setOnClickListener
            }
            else  if(password.isEmpty())
            {
                v.findViewById<TextView>(R.id.editTextTextPersonName3).error="Password can not be empty"
                return@setOnClickListener
            }
            //Entering valid email address
//            var email_validation=v.findViewById<TextView>(R.id.editTextTextPersonName2)
//            email_validation.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//
//                }
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
//                    {
//                        email_validation.setError("Invalid Email")
//                    }
//
//                }
//                override fun afterTextChanged(s: Editable?) {
//
//                }
//            })
            RetrofitClient.init().createUser(email, name, password)
                .enqueue(object : Callback<DataClassSignUp?>
                {
                    override fun onResponse(
                        call: Call<DataClassSignUp?>,
                        response: Response<DataClassSignUp?>
                    ) {
                        val status = response.body()?.status.toString()
                        Toast.makeText(activity, status, Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<DataClassSignUp?>, t: Throwable) {
                        Toast.makeText(
                            activity, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
        return v

    }
}