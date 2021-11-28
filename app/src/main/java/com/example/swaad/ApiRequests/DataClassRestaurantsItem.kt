package com.example.swaad.ApiRequests

data class DataClassRestaurantsItem(
    val id: Int,
    val user: Int,
    val image:String,
    val rest_name: String,
    val phone: String,
    val address: String,
    val no_of_ratings: Int,
    val avg_rating: Float

)