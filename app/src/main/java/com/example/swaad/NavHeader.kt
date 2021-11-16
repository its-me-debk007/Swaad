package com.example.swaad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.swaad.AuthPages.FragmentLogIn
import kotlinx.coroutines.launch

class NavHeader: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.nav_header, container, false)
        lifecycleScope.launch {
            val useremail = FragmentLogIn.readInfo("email")
            val name= FragmentLogIn.readInfo("name")
            v.findViewById<TextView>(R.id.haburger_name).text = "${name}"
            v.findViewById<TextView>(R.id.hamburger_email).text = useremail
        }
        return v
    }
}