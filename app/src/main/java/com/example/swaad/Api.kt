package com.example.swaad

import android.util.JsonWriter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Converter
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("/api/user/login/")
    fun logInUser
                (
        @Field("email")email: String,
        @Field("password")password: String
    ):Call<DataClass>


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
        @Body jsonConverter: JsonConverter
    ):Call<ResponseBody>

    @FormUrlEncoded
    @POST("/api/user/password/reset/")
    fun getSignUpOtp(
        @Field("email")email: String,
    ):Call<DataVerifyOtpClass>


    @FormUrlEncoded
    @POST("/api/user/password/reset/verify/")
    fun verifyOtp(
        @Field("email")email: String,
        @Field("otp")otp: String
    ):Call<DataVerifyOtpClass>


    @FormUrlEncoded
    @PATCH("/api/user/profile/")
    fun setNewPassword(
        @Field("password")password: String,
        @Header("Authorization")tokenString: String
    ):Call<DataSetNewPasswordClass>

}