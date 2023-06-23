package com.example.doan3.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.R
import com.example.doan3.adapter.FavouriteAdapter
import com.example.doan3.adapter.PaymentAdapter

import com.example.doan3.databinding.ActivityFavouriteBinding
import com.example.doan3.databinding.ActivityPaymentBinding
import com.example.doan3.model.ProductModel
import com.example.doan3.model.ShoppingCartModel
import com.example.doan3.utils.Utils

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var profil: SharedPreferences
    private lateinit var adapter: FavouriteAdapter
    val images = mutableListOf<ShoppingCartModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_favourite)
        profil = getSharedPreferences("login_session", MODE_PRIVATE)



        binding.recFavourite.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)


        val queue = Volley.newRequestQueue(this)
        val customer_id = profil.getInt("id",0).toString()
        val url =  Utils.BASE_URL + "doan3/LOG/ShowWishlist.php?customer_id=$customer_id"
        val images = mutableListOf<ProductModel>()

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val productId = item.getInt("productId")
                    val customer_id = item.getString("customer_id")
                    val productName = item.getString("productName")
                    val price = item.getString("price")
                    val imageUrl =
                        Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" + item.getString("image")
                    val image = ProductModel(productId, productName, customer_id,price, imageUrl)
                    images.add(image)
                }
                // Sử dụng adapter cho RecyclerView thông qua biến binding
                binding.recFavourite.adapter = FavouriteAdapter(images)
            },
            { error ->
                val message = when (error) {
                    is NoConnectionError -> "No internet connection"
                    is TimeoutError -> "Request timed out"
                    is AuthFailureError -> "Authentication failure"
                    is ServerError -> "Server error"
                    is NetworkError -> "Network error"
                    is ParseError -> "Data parsing error"
                    else -> "Unknown error"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            })


        queue.add(request)

        // Thêm view mới vào root view của layout
        setContentView(binding.root)


    }
    fun BackHome(view: View) {
        startActivity(Intent(this@FavouriteActivity, HomeActivity::class.java))
    }
}