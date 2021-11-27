package com.example.swaad

data class Order(
    val address: String,
    val created_at: String,
    val customer: Customer,
    val id: Int,
    val order_details: List<OrderDetailX>,
    val restaurant: Restaurant,
    val total: Int
)