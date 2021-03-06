package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.swaad.ApiRequests.DataClassRestaurantsItem
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.ForgotPassword2
import com.example.swaad.SearchPage2Files.SearchPage2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class search_page : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.fragment_search_page, container, false)

        val progressBar=v.findViewById<ProgressBar>(R.id.progressBarNew)

            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, SearchPage2())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
//                    progressBar.visibility=View.INVISIBLE
        return v
    }
}