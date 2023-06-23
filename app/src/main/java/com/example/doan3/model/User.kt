package com.example.doan3.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable{

        /**
         * Let' now come define instance fields for this class. We decorate them with
         * @SerializedName
         * attribute. Through this we are specifying the keys in our json data.
         */
        @SerializedName("id")
        var id: String?  = ""

        @SerializedName("name")
        var name: String? = ""


        @SerializedName("phone")
        var phone: String?  = ""

        @SerializedName("email")
        var email: String? = ""

        @SerializedName("address")
        var address: String? = ""

        @SerializedName("password")
        var password: String? = ""

        @SerializedName("zipcode")
        var zipcode: String? = ""


        @SerializedName("country")
        var country: String?  = ""







//    var name: String? = null
//    var phone: String? = null
//    var email: String? = null
//    var address: String? = null
//    var password: String? = null
//
//
//    fun getName(): String? {
//        return name
//    }
//
//    fun setNameFromString(name: String) {
//        this.name = name
//    }
//
//    fun getPhone(): String? {
//        return phone
//    }
//
//    fun setPhoneFromString(phone: String) {
//        this.phone = phone
//    }
//
//    fun getEmail(): String? {
//        return email
//    }
//
//    fun setEmailFromString(email: String) {
//        this.email = email
//    }
//
//    fun getAddress(): String? {
//        return address
//    }
//
//    fun setAddressFromString(address: String) {
//        this.address = address
//    }
//
//    fun getPassword(): String? {
//        return password
//    }
//
//    fun setPasswordFromString(password: String) {
//        this.password = password
//    }
}
