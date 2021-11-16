package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.swaad.AuthPages.FragmentLogIn
import kotlinx.coroutines.launch

class nav_header : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_nav_header, container, false)
        lifecycleScope.launch {
            val useremail = FragmentLogIn.readInfo("email")
            val name= FragmentLogIn.readInfo("name")
            v.findViewById<TextView>(R.id.haburger_name).text = "${name}"
            v.findViewById<TextView>(R.id.hamburger_email).text = useremail
        }
        return v
    }


}