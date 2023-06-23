package com.example.doan3.retrofit



import com.example.doan3.model.ResponseLogin
import com.example.doan3.model.UserModel
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiClient {



    @FormUrlEncoded
    @POST ( "doan3/LOG/Login.php")
    fun login(
        @Field( "post_email") email: String,
        @Field( "post_password") password: String
    ): Call<ResponseLogin>


    @FormUrlEncoded
    @POST("doan3/LOG/Register.php")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("country") country: String,
        @Field("city") city: String,
        @Field("address") address: String,
        @Field("zipcode") zipcode: String,
        ): Single<UserModel>

    @FormUrlEncoded
    @POST("doan3/LOG/add_to_cart.php") // Đường dẫn API PHP để thêm sản phẩm vào giỏ hàng
    fun addToCart(
        @Field("productName") productName: String,
        @Field("price") price: Int,
        @Field("quantity") quantity: Int,
        @Field("image") image: String
    ): Call<ResponseBody>



}

