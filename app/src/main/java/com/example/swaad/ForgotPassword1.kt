package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ForgotPassword1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_forgot_password_1, container, false)
        val next_button  = v.findViewById<Button>(R.id.forgotPasswordNextBtn)
        next_button.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,ForgotPassword2())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        return v
    }

}