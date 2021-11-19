package com.example.swaad.ApiRequests

import com.example.swaad.CategoryFoodItem
import com.example.swaad.JsonConverterCategory
import com.example.swaad.DataGetRestaurantNames
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




    @POST("api/user/signup/verify/")
    fun getSignUpOtp(
        @Body jsonConverterSignUpOtp: JsonConverterSignUpOtp
    ):Call<ResponseBody>

    @FormUrlEncoded
    @POST("/api/user/password/reset/verify/")
    fun verifyOtp(
        @Field("email")email: String,
        @Field("otp")otp: String
    ):Call<DataVerifyOtpClass>


    @FormUrlEncoded
    @PATCH("/api/user/profile/")
    fun setNewPassword(
        @Field("new_password")password: String,
        @Field("email")email: String
    ):Call<DataSetNewPasswordClass>
    @GET("/api/seller/customer/restaurants/")
    fun getRestaurantDetails():Call<List<DataClassRestaurantsItem>>

    @GET("/api/seller/customer/dish/{input}")
    fun getRestaurantDishes(@Path("input")  input : Int): Call<List<RestaurantDishesItem>>

  @POST("api/user/signup/sendotp/")
  fun resendOtpSignUp(@Body jsonConverterResendOtp:JsonConverter):Call<ResponseBody >

  @GET("/api/seller/customer/restaurants/list/")
  fun getRestaurantName(
      @Query("ordering")ordering: String,
      @Query("search")search: String
  ):Call<List<DataGetRestaurantNames>>
  @POST("api/seller/customer/restaurants/category/")
  fun categoryDish(@Body jsonConverterCategory: JsonConverterCategory):Call<List<CategoryFoodItem>>

  @POST("api/seller/ customer/rating/{dish_id}")
  fun dishRating(@Path("dish_id") dish_id:Int,@Body jsonConverterRating: JsonConverterRating):Call<ResponseBody>
//  @POST("api/cart/delivery/")
//  fun yourOrders(@Body jsonConverterOrders: JsonConverterOrders):Call<>
//  @POST
  @POST("api/cart/order/latest")
  fun orderUpdate(@Body jsonConverterOrderDetails: JsonConverterOrderDetails):Call<ResponseBody>
}