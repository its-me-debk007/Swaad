package com.example.swaad.ApiRequests

class JsonConverterSignUP(email:String,password:String,name:String) {
    val email:String
    val password:String
    val name:String
    init {
        this.email=email
        this.password=password
        this.name=name
    }
}