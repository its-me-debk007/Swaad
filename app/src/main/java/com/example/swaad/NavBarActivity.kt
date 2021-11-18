package com.example.swaad

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.swaad.NavBarPages.Home_page
import com.example.swaad.NavBarPages.MyCart
import com.example.swaad.NavBarPages.MyProfile
import com.example.swaad.SearchPage2Files.SearchPage2
import com.google.android.gms.wallet.callback.IntermediatePaymentData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class NavBarActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_bar)
        val drawerLayout=findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView=findViewById<NavigationView>(R.id.navView)
//findViewById<TextView>(R.id.hamburger_name).text=MyProfile.name
//        findViewById<TextView>(R.id.hamburger_email).text=MyProfile.useremail
//        val pay_button = findViewById<Button>(R.id.payButton)
//        pay_button.setOnClickListener {
//            makePayment()
//            PaymentNow("100")
//            Checkout.preload(container?.context)
//        }
        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, Home_page())
        fragmentTransaction.commit()

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.demoHome -> replaceFragment(Home_page())
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
