package com.example.doan3.model

data class PayLoadLogin (
    val id: Int,
     val  name: String,
    val  zipcode: Int,
    val  country: String,
    val  city: String,
    val  phone: Int,
     val address: String,
    val email : String,
    val password : String
)