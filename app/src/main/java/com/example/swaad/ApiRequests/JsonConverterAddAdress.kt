package com.example.swaad.ApiRequests

class JsonConverterAddAdress(phone:String,address:String,address_type:String) {
    var phone : String
    var address:String
    var address_type:String
    init {
        this.phone=phone
        this.address=address
        this.address_type=address_type
    }
}