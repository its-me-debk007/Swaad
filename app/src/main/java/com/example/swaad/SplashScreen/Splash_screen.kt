package com.example.swaad.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.NavBarActivity
import com.example.swaad.NavBarPages.Home_page
import com.example.swaad.R
import kotlinx.coroutines.launch


class Splash_screen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch{
            val value:Boolean? = FragmentLogIn.read("loggedIn")
            if(value==true)
            {
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, Home_page())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
                val intent = Intent(activity, NavBarActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v= inflater.inflate(R.layout.fragment_splash_screen, container, false)
        Handler().postDelayed(
            {
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            },3000)
        return v
    }

}