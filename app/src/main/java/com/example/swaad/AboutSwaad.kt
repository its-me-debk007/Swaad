package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.swaad.NavBarPages.MyProfile
import com.example.swaad.SearchPage2Files.SearchPage2

class AboutSwaad : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_about_swaad, container, false)
        val backButton=v.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,MyProfile())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        return v
    }


}