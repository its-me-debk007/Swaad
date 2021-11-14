package com.example.swaad.AuthPages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.example.swaad.*
import com.example.swaad.ApiRequests.DataClass
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.NavBarPages.home_page
import com.example.swaad.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FragmentLogIn: Fragment() {


    companion object{
        lateinit var NAME: String
        lateinit var userEmail: String
        var loggedIn:Boolean=false
        private var binding : ActivityMainBinding?=null
        private lateinit var dataStore: DataStore<Preferences>
        suspend fun save(key:String,value:Boolean)
        {
            val dataStoreKey= preferencesKey<Boolean>(key)
            dataStore.edit {Settings->
                Settings[dataStoreKey]=value
            }

        }
       suspend fun readInfo(key:String):String?
        {
            val dataStoreKey= preferencesKey<String>(key)
            val preferences = dataStore.data.first()
            return preferences[dataStoreKey]
        }suspend fun saveInfo(key:String,value:String)
        {
            val dataStoreKey= preferencesKey<String>(key)
            dataStore.edit {Settings->
                Settings[dataStoreKey]=value
            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dataStore = context?.createDataStore(name = "Settings")!!
        lifecycleScope.launch{
            val value:Boolean? = read("loggedIn")
            if(value==true)
            {
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, home_page())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
                val intent = Intent(activity, NavBarActivity::class.java)
                startActivity(intent)
            }
//            else
//            {
//                val intent = Intent(activity, NavBarActivity::class.java)
//                startActivity(intent)
//                val fragmentManager = activity?.supportFragmentManager
//                val fragmentTransaction = fragmentManager?.beginTransaction()
//                fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
//                fragmentTransaction?.addToBackStack(null)
//                fragmentTransaction?.commit()
//
//            }
        }

    }

    private suspend fun read(key:String):Boolean?
    {
        val dataStoreKey= preferencesKey<Boolean>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.user_login, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBar)
        val signUp : TextView = v.findViewById(R.id.loginSignUpText)
        signUp.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, ReferenceSignUp())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        val forgotPassword : TextView = v.findViewById(R.id.loginForgotPasswordText)
        forgotPassword.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, ForgotPassword1())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        val signInBtn : Button = v.findViewById(R.id.loginSignInBtn)
        signInBtn.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            signInBtn.isEnabled = false
            signInBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background2))
            userEmail = v.findViewById<TextInputEditText>(R.id.loginEmail2).text.toString().trim()
            val userPassword = v.findViewById<TextInputEditText>(R.id.loginPassword2).text.toString().trim()
            if(userEmail.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<EditText>(R.id.loginEmail2).error="Email can not be Empty"
                signInBtn.isEnabled = true
                signInBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener

            }
            if(userPassword.isEmpty())
            {

                progressBar.visibility=View.INVISIBLE
                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar2)
                v.findViewById<EditText>(R.id.loginPassword2).error="Please enter the password"
                signInBtn.isEnabled = true
                signInBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
                return@setOnClickListener

            }
//            Toast.makeText(activity,"Logging In",Toast.LENGTH_LONG).show()
//            RetrofitClient.init().logInUser(userEmail, userPassword).enqueue(object : Callback<DataClass?> {
//                override fun onResponse(call: Call<DataClass?>, response: Response<DataClass?>) {
//                    progressBar.visibility = View.INVISIBLE
//                    val responseBody = response.body()
//                    NAME = responseBody?.name.toString()
//                    if (responseBody?.token.toString() != "null") {
//                        loggedIn = true
//                        Toast.makeText(
//                            activity,
//                            "You've been logged in",
//                            Toast.LENGTH_LONG
//                        ).show()
//                        lifecycleScope.launch {
//                            save("loggedIn", loggedIn)
//                            saveInfo("email", userEmail)
//                            saveInfo("name", NAME)
//                        }
//
//                        val intent = Intent(activity, NavBarActivity::class.java)
//                        startActivity(intent)
//
//                    } else {
//                        Toast.makeText(
//                            activity,
//                            responseBody?.status,
//                            Toast.LENGTH_LONG).show()
//                        v.findViewById<TextInputEditText>(R.id.loginEmail2).text?.clear()
//                        v.findViewById<TextInputEditText>(R.id.loginPassword2).text?.clear()
//                        signInBtn.isEnabled = true
//                        signInBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
//                    }
//                }
//
//                override fun onFailure(call: Call<DataClass?>, t: Throwable) {
//                progressBar.visibility = View.INVISIBLE
//                Toast.makeText(
//                    activity,
//                    "Some error has been occurred!\n\nPlease try again",
//                    Toast.LENGTH_LONG
//                ).show()
//                    v.findViewById<TextInputEditText>(R.id.loginEmail2).text?.clear()
//                    v.findViewById<TextInputEditText>(R.id.loginPassword2).text?.clear()
//
//                    signInBtn.isEnabled = true
//                    signInBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button_background))
//            }
//            })
//

            val intent = Intent(activity, NavBarActivity::class.java)
            startActivity(intent)}
        return v
    }
}