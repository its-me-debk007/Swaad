package com.example.swaad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.swaad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
//        setContentView(binding.root)

        val signUp = findViewById<TextView>(R.id.loginSignUpText)
//        signUp.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this, ::class.java)
//            startActivity(intent)
//        })


    }
}