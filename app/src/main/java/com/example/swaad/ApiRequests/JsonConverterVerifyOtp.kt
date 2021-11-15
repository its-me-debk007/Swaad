package com.example.swaad.ApiRequests

class JsonConverterVerifyOtp(email:String ,otp:String) {
    var email:String
    var otp:String
    init{
        this.email=email
        this.otp=otp
    }
}