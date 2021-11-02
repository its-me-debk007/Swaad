package com.example.swaad

public class OtpJsonConverter(email: String, otp: String) {
    val email: String
    val otp: String
    init{
        this.email = email
        this.otp = otp
    }
}