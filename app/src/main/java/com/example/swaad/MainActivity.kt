package com.example.swaad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swaad.LayoutPages.FragmentLogIn

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, splash_screen())
        fragmentTransaction.commit()
    }
}