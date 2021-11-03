package com.example.swaad

import retrofit2.Call
import retrofit2.Converter
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("/api/user/login/")
    fun logInUser
                (
        @Field("email")email: String,
        @Field("password")password: String
    ):Call<DataClass>

    @FormUrlEncoded
    @POST("/api/user/password/reset/verify/")
    fun verifyOtp(
        @Field("email")email: String,
        @Field("otp")password: String
    ):Call<DataVerifyOtpClass>

}