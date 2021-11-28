package com.example.swaad.AuthPages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.swaad.NavBarPages.MyProfile
import com.example.swaad.R


class help_support : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_help_support, container, false)
        val back_button= v.findViewById<ImageView>(R.id.BackButton)
        back_button.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, MyProfile())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        val helpAndSupport=v.findViewById<Button>(R.id.chatWithUS)
        helpAndSupport.setOnClickListener {

            try{
                 var intent:Intent =  Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "swaad.info.contact@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                startActivity(intent);
            }catch(e:Exception){

            }
        }
        return v
    }


}