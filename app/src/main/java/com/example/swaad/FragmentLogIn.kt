package com.example.swaad

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.preference.PreferenceDataStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.swaad.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.prefs.Preferences

class FragmentLogIn: Fragment() {
    private var binding :ActivityMainBinding?=null
    private lateinit var dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
    private var loggedIn:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dataStore = context?.createDataStore(name = "Settings")!!
        lifecycleScope.launch{
            val value = read("loggedIn")
            if(value==true)
            {
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, home_page())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }
        }

    }
    private suspend fun save(key:String,value:Boolean)
    {
        val dataStoreKey= preferencesKey<Boolean>(key)
        dataStore.edit {Settings->
            Settings[dataStoreKey]=value
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

        val v = inflater.inflate(R.layout.fragment_login, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBar)
        val signUp : TextView = v.findViewById(R.id.loginSignUpText)

        val forgotPassword : TextView = v.findViewById(R.id.loginForgotPasswordText)
        forgotPassword.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,ForgotPassword1())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        val signInBtn : Button = v.findViewById(R.id.loginSignInBtn)
        signInBtn.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            val userEmail = v.findViewById<TextInputEditText>(R.id.loginEmail2).text.toString().trim()

            val userPassword = v.findViewById<TextInputEditText>(R.id.loginPassword2).text.toString().trim()

            if(userEmail.isEmpty())
            {
                progressBar.visibility=View.INVISIBLE
                v.findViewById<EditText>(R.id.loginEmail2).error="Email can not be Empty"
                return@setOnClickListener

            }
            if(userPassword.isEmpty())
            {

                progressBar.visibility=View.INVISIBLE
                val progressBar=v.findViewById<ProgressBar>(R.id.progressBar2)
                v.findViewById<EditText>(R.id.loginPassword2).error="Please enter the password"
                return@setOnClickListener

            }
            Toast.makeText(activity,"Logging In",Toast.LENGTH_LONG).show()

            RetrofitClient.init().logInUser(userEmail, userPassword).enqueue(object : Callback<DataClass?> {
                override fun onResponse(call: Call<DataClass?>, response: Response<DataClass?>) {
                    progressBar.visibility = View.INVISIBLE
                    val responseBody = response.body()
                    if (responseBody?.token.toString() != "null") {
                        loggedIn=true
                        Toast.makeText(activity, "You've been logged in", Toast.LENGTH_LONG).show()
                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fragment_container, home_page())
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    } else {
                        Toast.makeText(
                            activity,
                            "Wrong Credentials!!\n\nPlease check your email/password and try again!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    lifecycleScope.launch {
                        save("loggedIn",loggedIn)
                    }
                }
                override fun onFailure(call: Call<DataClass?>, t: Throwable) {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        activity,
                        "Wrong Credentials!!\n\nPlease check your email/password and try again!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

        }
        signUp.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,ReferenceSignUp())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }


        return v
    }
}