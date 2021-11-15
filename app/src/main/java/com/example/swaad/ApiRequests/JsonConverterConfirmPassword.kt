package com.example.swaad.ApiRequests

class JsonConverterConfirmPassword(new_password:String,email:String) {
    val email:String
    val new_password:String
    init {
        this.email=email
        this.new_password=new_password
    }
}