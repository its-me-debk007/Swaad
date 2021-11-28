package com.example.swaad.ApiRequests

class JsonConverterSignUpOtp(email:String,otp:String) {
    val email:String
    val otp:String
    init {
        this.email=email
        this.otp=otp
    }
}