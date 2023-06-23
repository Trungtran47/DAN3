package com.example.doan3.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.doan3.R

import com.example.doan3.databinding.ActivityDetailProductBinding
import com.example.doan3.utils.Utils
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import java.io.IOException


class DetailProduct : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var profil: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

//
        val btnDetTru = findViewById<ImageButton>(R.id.btnDetTru)
        val btnDetCong = findViewById<ImageButton>(R.id.btnDetCong)
        var quantity = 1
        fun updateQuantity(quantity: Int) {
            binding.txtCarQuantity.text = quantity.toString()
        }
        btnDetCong.setOnClickListener {
            if (quantity < 10) {
                quantity++
                updateQuantity(quantity)
            }
        }

// Xử lý sự kiện nút "Trừ"
        btnDetTru.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantity(quantity)
            }
        }
        val btnOrder = findViewById<ImageButton>(R.id.btnOrder)
        btnOrder.setOnClickListener {
            // Lấy thông tin sản phẩm từ intent
            val productId = intent.getIntExtra("productId", 0)
            val sId = profil.getString("email", null)
            val productName = intent.getStringExtra("productName")
            val price = intent.getStringExtra("price")
            val quantity = binding.txtCarQuantity.text.toString().toInt()


            val imageUrl = intent.getStringExtra("imageUrl")

            // Gọi phương thức để thêm dữ liệu vào MySQL
            if (price != null) {
                addToCart(productId, sId, productName, price, quantity, imageUrl)
                startActivity(Intent(this@DetailProduct, ShoppingCart::class.java))
            }
        }

        binding.btnWishlist.setOnClickListener {
            if (binding.btnWishlist.isPressed) {
                val drawable =
                    ContextCompat.getDrawable(this@DetailProduct, R.drawable.baseline_favorite_24)
                binding.btnWishlist.setImageDrawable(drawable)

                // Lấy thông tin sản phẩm từ intent
                val productId = intent.getIntExtra("productId", 0)
                val productName = intent.getStringExtra("productName")
                val customer_id = profil.getInt("id", 0)
                val price = intent.getStringExtra("price")
                val imageUrl = intent.getStringExtra("imageUrl")

                // Gọi phương thức để thêm dữ liệu vào MySQL

                if (price != null) {
                    addWislist(productName, imageUrl, price, productId, customer_id)
                }
            } else {
                val drawable = ContextCompat.getDrawable(
                    this@DetailProduct,
                    R.drawable.baseline_favorite_border_24
                )
                binding.btnWishlist.setImageDrawable(drawable)
            }
        }

        // Nhận dữ liệu sản phẩm từ intent
        val productId = intent.getIntExtra("productId", 0)
        val productName = intent.getStringExtra("productName")
        val productDesc = intent.getStringExtra("product_desc")
        val price = intent.getStringExtra("price")
        val imageUrl = intent.getStringExtra("imageUrl")

        // Hiển thị thông tin sản phẩm

        binding.detName.text = productName
        binding.detDesc.text = productDesc
        binding.detPice.text = price
        Glide.with(this).load(imageUrl).into(binding.detImage)
// Binh luan
        binding.btnComment.setOnClickListener {
            val customerName = profil.getString("name", null)
            val comment = binding.txtComment.text.toString().trim()
            val product_id = productId
            val rating = binding.ratingBar.rating

            if (rating > 0 && comment.isNotEmpty()) {
                Comment(customerName, comment, product_id)

                // Đánh dấu phản hồi đã được gửi thành công

                Toast.makeText(this, "Gửi phản hồi thành công.", Toast.LENGTH_SHORT).show()

                // Xóa dòng bình luận và số sao đã chọn
                binding.txtComment.text.clear()
                binding.ratingBar.rating = 0f
            } else {
                if (rating.equals(0f)) {
                    Toast.makeText(this, "Vui lòng chọn số sao.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Vui lòng nhập nội dung bình luận.", Toast.LENGTH_SHORT).show()
                }
            }
        }




    }
    private fun addToCart(productId: Int,sId: String?, productName: String?, price: String, quantity: Int, imageUrl: String?) {
        // Gửi dữ liệu sản phẩm lên server bằng cách sử dụng HTTP request hoặc thư viện HTTP như OkHttp hoặc Retrofit
        // Tạo một request và thêm dữ liệu sản phẩm vào body của request
        // Gửi request đến URL của mã PHP để thêm dữ liệu vào MySQL

        // sử dụng thư viện OkHttp:
        val client = OkHttpClient()

        val formBody = FormBody.Builder()
            .add("productId",productId.toString() )
            .add("productName", productName ?: "")
            .add("sId", sId?: "")
            .add("price", price)
            .add("quantity", quantity.toString())
            .add("image", imageUrl ?: "")
            .build()
        val request = Request.Builder()
            .url( Utils.BASE_URL + "doan3/LOG/add_to_cart.php")  // Thay YOUR_PHP_URL bằng URL của mã PHP để thêm dữ liệu vào MySQL
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Xử lý khi gặp lỗi trong quá trình gửi request
            }

            override fun onResponse(call: Call, response: Response) {
                // Xử lý khi nhận được phản hồi từ server
                val responseData = response.body.string()
                // Kiểm tra và xử lý phản hồi từ server (nếu cần)
            }
        })
    }

    private fun addWislist( productName: String?, imageUrl: String?, price: String,productId: Int, customer_id: Int) {
        // Gửi dữ liệu sản phẩm lên server bằng cách sử dụng HTTP request hoặc thư viện HTTP như OkHttp hoặc Retrofit
        // Tạo một request và thêm dữ liệu sản phẩm vào body của request
        // Gửi request đến URL của mã PHP để thêm dữ liệu vào MySQL
        // Ví dụ sử dụng thư viện OkHttp:
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add("productName", productName ?: "")
            .add("price", price)
            .add("image", imageUrl ?: "")
            .add("customer_id", customer_id.toString())
            .add("productId",productId.toString() )
            .build()
        val request = Request.Builder()
            .url( Utils.BASE_URL + "doan3/LOG/Insert_wishlist.php")  // Thay YOUR_PHP_URL bằng URL của mã PHP để thêm dữ liệu vào MySQL
            .post(formBody)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Xử lý khi gặp lỗi trong quá trình gửi request
            }
            override fun onResponse(call: Call, response: Response) {
                // Xử lý khi nhận được phản hồi từ server
                val responseData = response.body.string()
                // Kiểm tra và xử lý phản hồi từ server (nếu cần)
            }
        })
    }
    private fun Comment(customerName: String?, comment: String, product_id: Int){
        // sử dụng thư viện OkHttp:
        val client = OkHttpClient()

        val formBody = FormBody.Builder()
            .add("customerName", customerName ?: "")
            .add("comment", comment ?: "")
            .add("product_id", product_id.toString())
            .build()
        val request = Request.Builder()
            .url( Utils.BASE_URL + "doan3/LOG/Insert_Comment.php")  // Thay YOUR_PHP_URL bằng URL của mã PHP để thêm dữ liệu vào MySQL
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Xử lý khi gặp lỗi trong quá trình gửi request
            }

            override fun onResponse(call: Call, response: Response) {
                // Xử lý khi nhận được phản hồi từ server
                val responseData = response.body.string()
                // Kiểm tra và xử lý phản hồi từ server (nếu cần)
            }
        })
    }

    fun back(view: View) {
        startActivity(Intent(this@DetailProduct, HomeActivity::class.java))
    }
    fun shopping(view: View) {
        startActivity(Intent(this@DetailProduct, ShoppingCart::class.java))

    }




}