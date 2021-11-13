package com.example.swaad.ApiRequests

data class DataClassRestaurantsItem(
    val address: String,
    val avg_rating: Int,
    val id: Int,
    val no_of_ratings: Int,
    val phone: String,
    val rest_name: String,
    val user: Int
)