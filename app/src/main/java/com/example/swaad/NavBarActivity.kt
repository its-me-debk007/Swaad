package com.example.swaad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.swaad.NavBarPages.MyCart
import com.example.swaad.NavBarPages.MyProfile
import com.example.swaad.NavBarPages.home_page
import com.example.swaad.SearchPage2Files.SearchPage2
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.nav_bar)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, home_page())
        fragmentTransaction.commit()

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.demoHome -> replaceFragment(home_page())
                R.id.cart -> replaceFragment(MyCart())
                R.id.myProfile -> replaceFragment(MyProfile())

            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}