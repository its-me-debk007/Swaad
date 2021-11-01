package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ReferenceSignUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_reference_sign_up, container, false)

        val logIn : TextView = v.findViewById(R.id.textView7)
        logIn.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        val termsConditions : TextView = v.findViewById(R.id.textView5)
        termsConditions.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, TermsAndConditions())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        return v
    }
}