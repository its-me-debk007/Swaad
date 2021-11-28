package com.example.swaad.ApiRequests

data class OrderDetail(
    val dish_name: String,
    val dish_id: Int,
    val quantity: Int,
    val sub_total: Int,
    val order_detail_id: Int,
    val dish_price: Float
)