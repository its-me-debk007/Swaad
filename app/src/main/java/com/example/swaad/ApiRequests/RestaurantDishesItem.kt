package com.example.swaad.ApiRequests

data class RestaurantDishesItem(
    val id: Int,
    val photo: String,
    val price: Int,
    val title: String,
    val veg: Boolean
)