package com.example.swaad.NavBarPages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.swaad.*
import com.example.swaad.AuthPages.FragmentLogIn
//import com.example.swaad.AuthPages.FragmentLogIn.Companion.loggedIn
//import com.example.swaad.AuthPages.FragmentLogIn.Companion.loggedIn
//import com.example.swaad.AuthPages.FragmentLogIn.Companion.readInfo
import com.example.swaad.AuthPages.help_support
import com.example.swaad.ProfilePages.TermsAndConditions
import com.example.swaad.SplashScreen.Splash_screen
import com.example.swaad.SplashScreen.Splash_screen.Companion.loggedIn
import com.example.swaad.SplashScreen.Splash_screen.Companion.readInfo
//import com.example.swaad.SplashScreen.Splash_screen.Companion.loggedIn
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MyProfile: Fragment()  {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.profile_page, container, false)
        lifecycleScope.launch {
            var  useremail = readInfo("email").toString()
             var name= readInfo("name").toString()
            v.findViewById<TextView>(R.id.textView15).text = "Hi ${name}"
            v.findViewById<TextView>(R.id.textView23).text = useremail
        }
        val termsConditions: TextView = v.findViewById(R.id.textView21)
        termsConditions.setOnClickListener{
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, TermsAndConditions())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        val logOut: TextView = v.findViewById(R.id.textView22)
        val helpAndSupport=v.findViewById<TextView>(R.id.helpAndSupport)
        helpAndSupport.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, help_support())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()

        }
        val manageAdress=v.findViewById<TextView>(R.id.ManageAddress)
        manageAdress.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,Manage_Adress())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        logOut.setOnClickListener{
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
//            fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
            Splash_screen.loggedIn=false
            lifecycleScope.launch {
                Splash_screen.save("loggedIn", loggedIn!!)
            }
            val intent = Intent(activity, MainActivity()::class.java)
            startActivity(intent)
            Toast.makeText(
                activity,
                "You've been logged out successfully",
                Toast.LENGTH_LONG
            ).show()

        }

        return v
    }
}