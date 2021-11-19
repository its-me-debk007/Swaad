package com.example.swaad.ApiRequests

import com.example.swaad.OrderDetail

class JsonConverterOrderDetails(restaurant_id:Int,orderDetails:List<OrderDetail>) {
    val restaurant_id : Int
    val orderDetails:List<OrderDetail>
    init {
        this.restaurant_id=restaurant_id
        this.orderDetails=orderDetails
    }
}