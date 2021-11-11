package com.example.swaad

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swaad.LayoutPages.ForgotPassword1
import com.example.swaad.LayoutPages.FragmentLogIn


class splash_screen : Fragment() {

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_splash_screen, container, false)
        Handler().postDelayed(
            {
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, FragmentLogIn())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            },3000
        )
        return v
    }


}