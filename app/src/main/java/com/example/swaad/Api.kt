package com.example.swaad

import android.util.JsonWriter
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("/api/user/register/")
    fun createUser
                (
        @Field("email")email:String,
        @Field("name")name:String,
        @Field("password")password:String
    ):Call<DataClassSignUp>
    @POST("/api/user/password/reset/")
    fun getOtp (
        @Body  jsonConverter: JsonConverter
    ):Call<DataClassOtp>
}