package com.example.doan3.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.R
import com.example.doan3.adapter.MonAadapter
import com.example.doan3.databinding.MonaBinding
import com.example.doan3.model.ProductModel
import com.example.doan3.utils.Utils

class MonAActivity: AppCompatActivity() {
    private lateinit var binding: MonaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mona)
        binding = MonaBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val mLayoutManager = GridLayoutManager(this,4)
//        val mLayoutManager = LinearLayoutManager(this)
//        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        binding.monaRec.layoutManager = mLayoutManager

        val queue = Volley.newRequestQueue(this)
        val url =  Utils.BASE_URL + "doan3/LOG/ShowMonA.php"
        val images = mutableListOf<ProductModel>()

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val id = item.getInt("productId")
                    val desc = item.getString("product_desc")
                    val name = item.getString("productName")
                    val price = item.getString("price")
                    val imageUrl =
                        Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" + item.getString("image")
                    val image = ProductModel(id, name, desc,price, imageUrl)
                    images.add(image)
                }
                // Sử dụng adapter cho RecyclerView thông qua biến binding
                binding.monaRec.adapter = MonAadapter(images)
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


        mona()

    }

    private fun mona() {
        val recyclerView = findViewById<RecyclerView>(R.id.mona_rec)
        val mLayoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = mLayoutManager
    }

    fun BackHome(view: View) {
        startActivity(Intent(this@MonAActivity,HomeActivity::class.java))
    }
}