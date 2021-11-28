package com.example.swaad.ApiRequests

data class DataGetDishesList(
    val id: Int,
    val title: String,
    val price: Int,
    val category: String,
    val image: String,
    val restaurant_name: String,
    val restaurant_id: Int
)