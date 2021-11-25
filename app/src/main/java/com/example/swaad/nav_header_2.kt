package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.swaad.NavBarPages.MyProfile
import com.example.swaad.SplashScreen.Splash_screen
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [nav_header_2.newInstance] factory method to
 * create an instance of this fragment.
 */
class nav_header_2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_nav_header_2, container, false)
        lifecycleScope.launch {
            var useremail = Splash_screen.readInfo("email").toString()
            var name = Splash_screen.readInfo("name").toString()
            v.findViewById<TextView>(R.id.hamburger_name).text = "Hi ${name}"
            v.findViewById<TextView>(R.id.hamburger_email).text =useremail
        }
        return v
    }

}