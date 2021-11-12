package com.example.swaad

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.swaad.LayoutPages.FragmentLogIn.Companion.NAME
import com.example.swaad.LayoutPages.FragmentLogIn.Companion.logInStatus
//import com.example.swaad.LayoutPages.FragmentLogIn.Companion.logInStatus
import com.example.swaad.LayoutPages.FragmentLogIn.Companion.userEmail
import com.example.swaad.LayoutPages.TermsAndConditions

class MyProfile: Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.profile_page, container, false)

        v.findViewById<TextView>(R.id.textView15).text = "Hi ${NAME}"
        v.findViewById<TextView>(R.id.textView23).text = userEmail

        val termsConditions: TextView = v.findViewById(R.id.textView21)
        termsConditions.setOnClickListener{
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, TermsAndConditions())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        val logOut: TextView = v.findViewById(R.id.textView22)
        logOut.setOnClickListener{
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
//            fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
            logInStatus = false
            val intent = Intent(activity, MainActivity::class.java)
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