package com.example.swaad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import javax.security.auth.callback.Callback

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment_container,FragmentLogIn())
//        fragmentTransaction.commit()

//        val apidatafetch = APIdataFetch.create().getData()
//        //apiInterface.enqueue( Callback<List<Movie>>())
//        apidatafetch.enqueue( object : retrofit2.Callback<APIdata> {
//            override fun onResponse(call: Call<APIdata>?, response: Response<APIdata>?) {
//
//                if(response?.body() != null)
//                    Log.d("APUN KI CODE", response.body()!!.toString())
//            }
//
//            override fun onFailure(call: Call<APIdata>?, t: Throwable?) {
//                Log.d("Apun ka code", "data not found")
//            }
//        })
        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : retrofit2.Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!
                val myStringBuilder = StringBuilder()

                for(myData in responseBody){
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }
                findViewById<TextView>(R.id.txtId).text = myStringBuilder
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity","onFailure "+t.message)
            }
        })

    }
}