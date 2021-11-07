package com.example.swaad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.swaad.LayoutPages.FragmentLogIn.Companion.NAME
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


        return v
    }
}