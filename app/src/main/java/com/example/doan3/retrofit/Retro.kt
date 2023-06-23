
package com.example.doan3.retrofit


import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Retro {
    private var instance: Retrofit? = null
    fun getInstance(baseUlr: String): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(baseUlr)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
        return instance!!
    }
}