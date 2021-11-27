package com.example.swaad.ApiRequests

data class DataClassCartInfo(
    val order_details: List<OrderDetail>,
    val order_total: Int,
    val restaurant_id: Int
)