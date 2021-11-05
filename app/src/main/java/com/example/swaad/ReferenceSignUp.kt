package com.example.swaad

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
import android.widget.*

class ReferenceSignUp : Fragment() {
    companion object{
        lateinit var nextPage: String
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
        val unsee=v.findViewById<ImageView>(R.id.unsee_password)
        var flag =0
        val password=v.findViewById<EditText>(R.id.editTextTextPersonName3)
        unsee.setOnClickListener {
            if(flag%2==0) {
                password.transformationMethod =HideReturnsTransformationMethod.getInstance()
                password.setSelection(password.getText().length);
                flag++
            }
            else
            {
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                password.setSelection(password.getText().length);
                flag++
            }
//            password.setTextCursorDrawable(password.getText().length)
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
            progressBar.visibility=View.VISIBLE
            val name = v.findViewById<TextView>(R.id.editTextTextPersonName).text.toString().trim()
            val email =
                v.findViewById<TextView>(R.id.editTextTextPersonName2).text.toString().trim()
            val password =
                v.findViewById<TextView>(R.id.editTextTextPersonName3).text.toString().trim()

            if(name.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<TextView>(R.id.editTextTextPersonName).error="Username can not be empty"
                return@setOnClickListener
            }
            else if(email.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<TextView>(R.id.editTextTextPersonName2).error="Email can not be empty"
                return@setOnClickListener
            }
            else if(password.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<TextView>(R.id.editTextTextPersonName3).error="Password can not be empty"
                return@setOnClickListener
            }

            Toast.makeText(activity,"Please wait !", Toast.LENGTH_LONG).show()
            RetrofitClient.init().createUser(email, name, password).enqueue(object : Callback<DataClassSignUp?>{
                    override fun onResponse(
                        call: Call<DataClassSignUp?>,
                        response: Response<DataClassSignUp?>
                    ) {
                        progressBar.visibility=View.INVISIBLE
                        val status = response.body()?.status.toString()
                        Toast.makeText(activity, status, Toast.LENGTH_LONG).show()

                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    }

                    override fun onFailure(call: Call<DataClassSignUp?>, t: Throwable) {
                        progressBar.visibility=View.INVISIBLE
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