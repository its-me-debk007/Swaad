package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.ApiRequests.DataClassGetManageAdressItem
import com.example.swaad.ApiRequests.RecyclerAdapterManageAddress
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.SplashScreen.Splash_screen
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Manage_Adress : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterManageAddress.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val v= inflater.inflate(R.layout.fragment_manage__adress, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBarManageAdress)

        var Token:String="asdasdas"
        lifecycleScope.launch{
            var AccessToken = Splash_screen.readInfo("accessToken").toString()
            Token =AccessToken
        }

        RetrofitClient.init().getManageAddress(token="Bearer $Token").enqueue(object : Callback<List<DataClassGetManageAdressItem>?> {
            override fun onResponse(
                call: Call<List<DataClassGetManageAdressItem>?>,
                response: Response<List<DataClassGetManageAdressItem>?>
            ) {
                progressBar.visibility  = View.VISIBLE
                var responseBody=response?.body()
                if(response.isSuccessful) {
                    progressBar.visibility=View.INVISIBLE
                    layoutManager = LinearLayoutManager(container?.context)
                    var recyclerAdapter = v.findViewById<RecyclerView>(R.id.recyclerViewManageAdress)
                    recyclerAdapter.layoutManager=layoutManager
                    adapter= responseBody?.let {
                        RecyclerAdapterManageAddress(container!!.context,
                            it
                        )
                    }
                    recyclerAdapter.adapter=adapter
                }
                else
                { progressBar.visibility=View.INVISIBLE
                    Toast.makeText(activity,response.body().toString(),Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<DataClassGetManageAdressItem>?>, t: Throwable) {
                progressBar.visibility=View.INVISIBLE
                Toast.makeText(activity,t.message,Toast.LENGTH_LONG).show()
            }
        })
        val addAddres=v.findViewById<Button>(R.id.addAdress)
        addAddres.setOnClickListener{
            val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container,AddingAdress())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
        }
        return v
    }


}