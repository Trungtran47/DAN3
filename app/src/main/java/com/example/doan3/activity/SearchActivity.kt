package com.example.doan3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.adapter.MonAadapter
import com.example.doan3.adapter.SearchAdapter
import com.example.doan3.databinding.ActivitySearchBinding
import com.example.doan3.model.ProductModel
import com.example.doan3.utils.Utils
import org.json.JSONArray

import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    private lateinit var adapter: SearchAdapter
    private lateinit var binding: ActivitySearchBinding
    private var searchList: List<ProductModel> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recSearch
        searchView = binding.searchView

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter(searchList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Không cần xử lý
                return false
            }

            override fun onQueryTextChange(productName: String?): Boolean {
                productName?.let { searchProductByName(it) }
                return true
            }
        })
    }

    private fun searchProductByName(productName: String) {
        val queue = Volley.newRequestQueue(this)
        val url =  Utils.BASE_URL + "doan3/LOG/Search.php?productName=$productName"
        val images = mutableListOf<ProductModel>()

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val productId = item.getInt("productId")
                    val product_desc = item.getString("product_desc")
                    val productName = item.getString("productName")
                    val price = item.getString("price")
                    val imageUrl =
                        Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" + item.getString("image")
                    val image = ProductModel(productId, productName, product_desc, price, imageUrl)
                    images.add(image)
                }

                adapter.setData(filterProductsByName(images, productName))
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
        // Thêm request vào hàng đợi

    }

    private fun filterProductsByName(products: List<ProductModel>, productName: String): List<ProductModel> {
        val filteredList = mutableListOf<ProductModel>()
        val query = productName.toLowerCase(Locale.getDefault())

        for (product in products) {
            val name = product.productName.toLowerCase(Locale.getDefault())
            if (name.contains(query)) {
                filteredList.add(product)
            }
        }

        return filteredList
    }


}
