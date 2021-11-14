package com.example.swaad.ApiRequests

class JsonConverterConfirmPassword(email :String,newpassword:String) {
    val email:String
    val newPassword:String
    init {
        this.email=email
        this.newPassword=newpassword
    }
}