package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class ForgotPassword2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.fragment_forgot_password_2, container, false)
        val verify_button : Button = v.findViewById(R.id.verify_button)
        verify_button.setOnClickListener{
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container,ForgotPassword3())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
        }
        return v
    }
}
