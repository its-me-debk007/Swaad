package com.example.swaad

data class OrderDetails(
    val order_details: List<OrderDetail>,
    val restaurant_id: Int
)