package com.example.doan3.retrofit


import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private fun getRetrofitClient(): Retrofit {
    return Retrofit.Builder()
        .baseUrl( "http://192.168.1.9/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    fun getInstance(): ApiClient {
        return getRetrofitClient().create(ApiClient::class.java)

    }
}

