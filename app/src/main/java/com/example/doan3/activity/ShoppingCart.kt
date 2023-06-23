package com.example.doan3.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.R
import com.example.doan3.adapter.ShoppingCartAdapter
import com.example.doan3.databinding.ActivityShoppingCartBinding
//import com.example.doan3.`interface`.QuantityChangeListener
import com.example.doan3.model.ShoppingCartModel
import com.example.doan3.utils.Utils
import java.net.URLEncoder




class ShoppingCart : AppCompatActivity()  {
    private lateinit var binding: ActivityShoppingCartBinding
    private lateinit var adapter: ShoppingCartAdapter
    val images = mutableListOf<ShoppingCartModel>()
    private lateinit var profil: SharedPreferences





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_shopping_cart)
        binding.cartRec.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)
        adapter = ShoppingCartAdapter(images)
        binding.cartRec.adapter = adapter




        profil = getSharedPreferences("login_session", MODE_PRIVATE)
        val queue = Volley.newRequestQueue(this)
        val sId = profil.getString("email", null)
        val encodedSId = URLEncoder.encode(sId, "UTF-8")
        val url =  Utils.BASE_URL + "doan3/LOG/ShowProductCart.php?sId=$encodedSId"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val cartId = item.getInt("cartId")
                    val productId = item.getInt("productId")
                    val sId = profil.getString("email", null)
                    val name = item.getString("productName")
                    val price = item.getInt("price")
                    val quantity = item.getInt("quantity")


                    val imageUrl =  Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" +item.getString("image")
//
                    val image = sId?.let { ShoppingCartModel(cartId,productId,it,imageUrl, name,price,quantity) }
                    if (image != null) {
                        images.add(image)
                    }
                }


                // Sử dụng adapter cho RecyclerView thông qua biến binding
                binding.cartRec.adapter = ShoppingCartAdapter(images)

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



        binding.btnUpdate.setOnClickListener{

            val totalPrice = adapter.calculateTotalPrice()
            binding.txtTotalPrice.text = totalPrice.toString()


        }

        binding.btnBuy.setOnClickListener {
            val totalPrice = adapter.calculateTotalPrice()
            val intent: Intent = Intent (this, Payment::class.java)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)

        }

    }





    fun back(view: View) {
        startActivity(Intent(this@ShoppingCart, HomeActivity::class.java))
    }

    fun infoOrder(view: View) {
        startActivity(Intent(this@ShoppingCart, InforOrder::class.java))
    }


//    fun buy(view: View) {
//        startActivity(Intent(this@ShoppingCart, Payment::class.java))
//
//    }


}
