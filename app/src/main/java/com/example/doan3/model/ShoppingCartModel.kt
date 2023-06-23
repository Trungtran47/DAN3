package com.example.doan3.model

data class ShoppingCartModel(
        val cartId: Int,
        val productId: Int,
        val sId: String,
        val image: String,
        val productName: String,
        val price: Int,
        var quantity: Int,

        )