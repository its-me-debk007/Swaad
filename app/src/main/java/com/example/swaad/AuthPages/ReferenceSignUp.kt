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
import androidx.lifecycle.lifecycleScope
import com.example.swaad.ApiRequests.*
import com.example.swaad.R
import com.example.swaad.ProfilePages.TermsAndConditions
import com.example.swaad.otp_sign_up
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

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
            fragmentTransaction?.addToBackStack("tag")
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
            fragmentTransaction?.addToBackStack("tag")
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
                v.findViewById<EditText>(R.id.editTextTextPersonName).setError("Username can not be empty")
                sign_up.isEnabled = true
                sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
            if(!isValidEmail(emailSignUp)){
                progressBar.visibility=View.INVISIBLE
                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar2)
                v.findViewById<EditText>(R.id.signUpEmail).setError("Please enter a valid email")
                sign_up.isEnabled = true
                sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
//            if(password.length < 5)
//            {
//                progressBar.visibility=View.INVISIBLE
//                v.findViewById<TextInputEditText>(R.id.Sign_up_password).error="Minimum length of password should be 5 characters"
//                sign_up.isEnabled = true
//                sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
//                return@setOnClickListener
//            }
            lifecycleScope.launch {

            }
            var flagLower = false
            var flagUpper = false
            var flagNumber = false
            for(i in 0..password.length-1){
                if(password[i] in 'a'..'z')
                    flagLower = true
                if(password[i] in 'A'..'Z')
                    flagUpper = true
                if(password[i] in '0'..'9')
                    flagNumber = true
            }
            if(!(flagLower && flagUpper && flagNumber) || password.length < 5){
                progressBar.visibility=View.INVISIBLE
                v.findViewById<TextInputEditText>(R.id.Sign_up_password).setError("Minimum length of password should be 5 characters\n\nThere should be atleast one uppercase, lowercase and a numeric digit")
                sign_up.isEnabled = true
                sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener
            }
    val jsonConverterSignUp=JsonConverterSignUP(emailSignUp,password,name)
//            Toast.makeText(activity,"Please wait !", Toast.LENGTH_LONG).show()
            RetrofitClient.init().createUser(jsonConverterSignUp).enqueue(object : Callback<ResponseBody?>{
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {

//                        Toast.makeText(activity, response.code(), Toast.LENGTH_LONG).show()
                        if(response.isSuccessful) {
                            progressBar.visibility=View.INVISIBLE
//                            Toast.makeText(activity, status, Toast.LENGTH_LONG).show()
                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.fragment_container, otp_sign_up())
                            fragmentTransaction?.addToBackStack("tag")
                            fragmentTransaction?.commit()
                        }
                        else if(response.code()==406){
                            progressBar.visibility=View.INVISIBLE
                            Toast.makeText(activity,"Registration was not successful. please enter the details carefully", Toast.LENGTH_LONG).show()
//                            v.findViewById<EditText>(R.id.editTextTextPersonName).text.clear()
                            v.findViewById<TextInputEditText>(R.id.Sign_up_password).text?.clear()

                            sign_up.isEnabled = true
                            sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                        }
                        else if(response.code()==403)
                        {
                            progressBar.visibility=View.INVISIBLE
                            Toast.makeText(activity,"Entered email is already registered", Toast.LENGTH_LONG).show()
//                            v.findViewById<EditText>(R.id.editTextTextPersonName).text.clear()
                            v.findViewById<TextInputEditText>(R.id.Sign_up_password).text?.clear()
                            sign_up.isEnabled = true
                            sign_up.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
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