package com.example.swaad.ApiRequests

data class OrderDetail(
    val dish: Dish,
    val dish_name: String,
    val id: Int,
    val quantity: Int,
    val sub_total: Int
)