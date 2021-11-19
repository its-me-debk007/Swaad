package com.example.swaad.ApiRequests

class JsonConverterOrderDetails(restaurant_id:String,orderDetails:List<String>) {
    val restaurant_id : String
    val orderDetails:List<String>
    init {
        this.restaurant_id=restaurant_id
        this.orderDetails=orderDetails
    }
}