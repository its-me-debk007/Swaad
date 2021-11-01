package com.example.swaad

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("posts")
    // For Queries: fun getData(@Query("country")country: String, @Query("page")page: Int)
    fun getData(): Call<List<MyDataItem>>
}