package com.example.doan3.model

data class Order(
    val id : Int,
    val productName: String,
    val image: String,
    val price: Int,
    var quantity: Int,
    var status: Int,

    )
