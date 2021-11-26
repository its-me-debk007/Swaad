package com.example.swaad.ApiRequests

import com.example.swaad.AuthPages.FragmentLogIn.Companion.readInfo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.NavBarPages.Home_page.Companion.AccessToken

object RetrofitClient {
    private val client = OkHttpClient.Builder().build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://django-rest-swaad.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
//            .client(OkHttpClient.Builder().addInterceptor { chain ->
//                val request =
//                    chain.request().newBuilder().addHeader("Authorization", "Bearer ${AccessToken}")
//                        .build()
//                chain.proceed(request)
//            }.build())
            .client(client)
            .build()

    fun init(): Api {
        return retrofit.create(Api::class.java)
    }
}
