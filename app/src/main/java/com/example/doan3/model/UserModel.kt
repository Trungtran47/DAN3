package com.example.doan3.model

data class UserModel (
    var success: Boolean,
    var message: String,
    var result: List<User>
)
//package com.example.doan3.model
//
//import com.google.gson.annotations.SerializedName
//
//class UserModel {
//
//    data class UserModel (
//        var success: Boolean,
//        var message: String,
//        var result: List<User>
//    )
//    @SerializedName("result")
//    var result: ArrayList<User>? = ArrayList()
//
//
//
//}
