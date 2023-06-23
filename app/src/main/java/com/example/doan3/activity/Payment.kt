package com.example.doan3.activity



import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.R
import com.example.doan3.adapter.PaymentAdapter
import com.example.doan3.adapter.ShoppingCartAdapter
import com.example.doan3.databinding.ActivityPaymentBinding
import com.example.doan3.databinding.ActivityShoppingCartBinding
import com.example.doan3.model.ShoppingCartModel
import com.example.doan3.utils.Utils
import okhttp3.*
import okhttp3.Response
import java.io.IOException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*
import okhttp3.OkHttpClient
import okhttp3.Callback
import okhttp3.FormBody

class Payment : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var profil: SharedPreferences
    private lateinit var adapter: PaymentAdapter
    val images = mutableListOf<ShoppingCartModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_payment)
        profil = getSharedPreferences("login_session", MODE_PRIVATE)


        binding.recPayment.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)
        adapter = PaymentAdapter(images)
        binding.recPayment.adapter = adapter

        // hiển thi thông tin tất cả đơn hàng trong giỏ hàng


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


                        val imageUrl =
                            Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" + item.getString("image")
//
                        val image = sId?.let {
                            ShoppingCartModel(cartId,productId, it, imageUrl, name, price, quantity
                            )
                        }

                        if (image != null) {
                            images.add(image)
                        }

                }
                // Sử dụng adapter cho RecyclerView thông qua biến binding
                binding.recPayment.adapter = PaymentAdapter(images)

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

// het


        binding?.txtPayName?.text = profil.getString("name", null)
        val textFromTextView = profil.getString("address", null)
        binding?.txtPayAddress?.setText(textFromTextView)

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        val txtDateTime = findViewById<TextView>(R.id.textView)
        txtDateTime.text = formattedDate

        binding.btnUpdatePay.setOnClickListener {
            val intent = intent
            val totalPrice : Int = intent.getIntExtra("totalPrice", 0)   //0 là default value

            val textView = binding.txtPayTotal
            textView.text = totalPrice.toString()
        }

        val paymentAdapter = PaymentAdapter(images)

        adapter = paymentAdapter


        binding.btnPayment.setOnClickListener {
            // Lấy thông tin sản phẩm từ intent
            val selectedItems = paymentAdapter.getItems()
            val customer_id = profil.getInt("id", 0)
            for (item in selectedItems) {
                val productId = item.productId
                val productName = item.productName
                val quantity = item.quantity
                val imageUrl = item.image
                val price = item.price

                // Thực hiện xử lý với các giá trị này, ví dụ: đẩy dữ liệu lên tbl_order của MySQL
                // ...
                addToOrder(productId,customer_id, productName, price, quantity, imageUrl)
            }
            Toast.makeText(
                this@Payment,
                "Bạn đã mua hàng thành công ",
                Toast.LENGTH_LONG
            ).show()

            startActivity(Intent(this@Payment, InforOrder::class.java))

        }
    }
    private fun addToOrder(productId: Int, customer_id: Int, productName: String?, price: Int, quantity: Int, imageUrl: String?) {
        // Gửi dữ liệu sản phẩm lên server bằng cách sử dụng HTTP request hoặc thư viện HTTP như OkHttp hoặc Retrofit
        // Tạo một request và thêm dữ liệu sản phẩm vào body của request
        // Gửi request đến URL của mã PHP để thêm dữ liệu vào MySQL

        // Ví dụ sử dụng thư viện OkHttp:
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add("productId",productId.toString() )
            .add("customer_id", customer_id.toString())
            .add("productName", productName ?: "")
            .add("price", price.toString())
            .add("quantity", quantity.toString())
            .add("image", imageUrl ?: "")
            .build()
        val request = okhttp3.Request.Builder()
            .url( Utils.BASE_URL + "doan3/LOG/insert_order.php")  // Thay YOUR_PHP_URL bằng URL của mã PHP để thêm dữ liệu vào MySQL
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


    fun BackCart(view: View) {
        startActivity(Intent(this@Payment, ShoppingCart::class.java))
    }




//    fun payment(view: View) {
//        if (binding.radioButton.isChecked) {
//            binding.radio.clearCheck()
//            startActivity(Intent(this@Payment, InforOrder::class.java))
//        }
//        else {
//            startActivity(Intent(this@Payment, HomeActivity::class.java))
//        }
//    }

}