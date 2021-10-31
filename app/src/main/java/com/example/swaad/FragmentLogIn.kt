package com.example.swaad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentLogIn: Fragment(R.layout.fragment_login) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_login, container, false)
        val sign_up : TextView = v.findViewById(R.id.loginSignUpText)
        sign_up.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,reference_sign_up())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        return v
    }
}