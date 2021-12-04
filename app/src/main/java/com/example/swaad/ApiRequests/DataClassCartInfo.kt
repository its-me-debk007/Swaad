package com.example.swaad.ApiRequests

import com.example.swaad.OrderDetailX

data class DataClassCartInfo(
    val order_details: List<OrderDetailX>,
    val order_total: Int,
    val restaurant_id: Int,
    val status: String,
    val restaurant_name: String
)