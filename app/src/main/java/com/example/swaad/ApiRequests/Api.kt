package com.example.swaad.ApiRequests

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("/api/user/login/")
    fun logInUser
                (
        @Field("email")email: String,
        @Field("password")password: String
    ):Call<DataClass>

    @POST("/api/user/register/")
    fun createUser(@Body jsonconvertersignup:JsonConverterSignUP):Call<ResponseBody>


    @POST("/api/user/password/reset/")
    fun getOtp (
        @Body jsonConverter: JsonConverter
    ):Call<ResponseBody>

//    @FormUrlEncoded
//    @POST("/api/user/login/otp")
//    fun getSignInOtp (
//        @Field("email")email: String
//    ):Call<DataSignInOtp>

    @FormUrlEncoded
    @POST("/api/user/login/verify")
    fun verifySignInOtp (
        @Field("email")email: String,
        @Field("otp")otp: String
    ):Call<DataVerifySignInOtp>


    @POST("api/user/signup/verify/")
    fun getSignUpOtp(
        @Body jsonConverterSignUpOtp: JsonConverterSignUpOtp
    ):Call<ResponseBody>


    @POST("/api/user/password/reset/verify/")
    fun verifyOtp(
        @Body jsonConverterVerifyOtp: JsonConverterVerifyOtp
    ):Call<ResponseBody>

    @POST("/api/user/profile/")
    fun setNewPassword(
        @Body jsonConverterConfirmPassword: JsonConverterConfirmPassword):Call<ResponseBody>
    @GET("/api/seller/customer/restaurants/")
    fun getRestaurantDetails():Call<List<DataClassRestaurantsItem>>

    @GET("/api/seller/customer/dish/{input}")
    fun getRestaurantDishes(@Path("input")  input : Int): Call<List<RestaurantDishesItem>>

  @POST("api/user/signup/sendotp/")
  fun resendOtpSignUp(@Body jsonConverterResendOtp:JsonConverter):Call<ResponseBody >

}