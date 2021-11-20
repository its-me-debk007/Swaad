package com.example.swaad.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.NavBarActivity
import com.example.swaad.NavBarPages.Home_page
import com.example.swaad.R
import com.example.swaad.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class Splash_screen : Fragment(){
    //
    companion object{

//        lateinit var token:String
//        lateinit var NAME: String
//        lateinit var userEmail: String
//        lateinit var loginOtpEmail: String
        var loggedIn:Boolean?=true
        private var binding : ActivityMainBinding?=null
        var dataStore: DataStore<Preferences>? = null
        suspend fun save(key:String,value:Boolean)
        {
            val dataStoreKey= preferencesKey<Boolean>(key)
            dataStore?.edit {Settings->
                Settings[dataStoreKey]=value
            }

        }
        suspend fun readInfo(key:String):String?
        {
            val dataStoreKey= preferencesKey<String>(key)
            val preferences = dataStore?.data?.first()
            return preferences?.get(dataStoreKey)
        }
        suspend fun saveInfo(key:String,value:String)
        {
            val dataStoreKey= preferencesKey<String>(key)
            dataStore?.edit { Settings->
                Settings[dataStoreKey]=value
            }

        }
        suspend fun read(key:String):Boolean?
        {
            val dataStoreKey= preferencesKey<Boolean>(key)
            val preferences = dataStore?.data?.first()
            return preferences?.get(dataStoreKey)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dataStore = context?.createDataStore(name = "Settings")!!
//        lifecycleScope.launch{
//            val value:Boolean? = FragmentLogIn.read("loggedIn")
//            if(value==true)
//            {
////                val fragmentManager = activity?.supportFragmentManager
////                val fragmentTransaction = fragmentManager?.beginTransaction()
////                fragmentTransaction?.replace(R.id.fragment_container, Home_page())
////                fragmentTransaction?.addToBackStack(null)
////                fragmentTransaction?.commit()
//                val intent = Intent(activity, NavBarActivity::class.java)
//                startActivity(intent)
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_splash_screen, container, false)
//        Handler().postDelayed(
//            {
//                val fragmentManager = activity?.supportFragmentManager
//                val fragmentTransaction = fragmentManager?.beginTransaction()
//                fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
//                fragmentTransaction?.addToBackStack(null)
//                fragmentTransaction?.commit()
//            },3000)
//        Handler().postDelayed(
//            {
        lifecycleScope.launch {
            var loggedIn = read("loggedIn")
            Toast.makeText(activity, loggedIn.toString(), Toast.LENGTH_LONG)
                .show()
        }
        lifecycleScope.launch {
         var loggedIn =read("loggedIn")
            Toast.makeText(activity, loggedIn.toString(),Toast.LENGTH_LONG).show()
//            if(loggedIn==true || loggedIn==null)
//            {
//                val fragmentManager = activity?.supportFragmentManager
//                val fragmentTransaction = fragmentManager?.beginTransaction()
//                fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
//                fragmentTransaction?.addToBackStack(null)
//                fragmentTransaction?.commit()
//            }
             if (loggedIn == true) {
//                val fragmentManager = activity?.supportFragmentManager
//                val fragmentTransaction = fragmentManager?.beginTransaction()
//                fragmentTransaction?.replace(R.id.fragment_container, Home_page())
//                fragmentTransaction?.addToBackStack(null)
//                fragmentTransaction?.commit()
                val intent = Intent(activity, NavBarActivity::class.java)
                startActivity(intent)
            }
            else
             {
                 val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
             }
        }
//        },3000)
//        lifecycleScope.launch{
//            val value:Boolean? = FragmentLogIn.read("loggedIn")
//            if(value==true)
//            {
////                val fragmentManager = activity?.supportFragmentManager
////                val fragmentTransaction = fragmentManager?.beginTransaction()
////                fragmentTransaction?.replace(R.id.fragment_container, Home_page())
////                fragmentTransaction?.addToBackStack(null)
////                fragmentTransaction?.commit()
//                val intent = Intent(activity, NavBarActivity::class.java)
//                startActivity(intent)
//            }
        return v
    }


}