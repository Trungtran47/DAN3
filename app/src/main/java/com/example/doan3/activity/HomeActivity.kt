package com.example.doan3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.R
//import com.example.my.FeatureAdapter.onItemClickListener
import com.example.doan3.adapter.NewprAdapter
import com.example.doan3.adapter.FeatureAdapter
import com.example.doan3.adapter.HomeAdapter
import com.example.doan3.databinding.ActivityHomeBinding

import com.example.doan3.model.HomeModel
import com.example.doan3.model.ProductModel
import com.example.doan3.utils.Utils


class HomeActivity : AppCompatActivity() {

    val homeList = listOf<HomeModel>(
        HomeModel(R.drawable.mona,"Asian Food"),
        HomeModel(R.drawable.monau,"Euro Food"),
        HomeModel(R.drawable.haisan,"Seafood"),
        HomeModel(R.drawable.douong,"Drinks")
    )


    private var imageList = intArrayOf(
        R.drawable.slider1,
        R.drawable.slider2,
        R.drawable.slider3
    )

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewFlipper()
        homeProduct()
        homeFeature()
        homeNew()
    }

    private fun homeFeature() {
        // Khai báo biến binding để lấy tham chiếu đến RecyclerView


        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.homeProductRec.layoutManager = mLayoutManager

        val queue = Volley.newRequestQueue(this)
        val url = Utils.BASE_URL + "doan3/LOG/ShowFr.php"
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
                binding.homeProductRec.adapter = FeatureAdapter(images)
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




    private fun homeProduct(){
        val recyclerView = findViewById<RecyclerView>(R.id.home_hor_rec)
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = HomeAdapter(homeList,this@HomeActivity)
    }

    private fun homeNew(){
        // Khai báo biến binding để lấy tham chiếu đến RecyclerView


        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.homeNewRec.layoutManager = mLayoutManager

        val queue = Volley.newRequestQueue(this)
        val url =  Utils.BASE_URL + "doan3/LOG/ShowNew.php"
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
                    val imageNew = ProductModel(id, name, desc,price, imageUrl)
                    images.add(imageNew)
                }
                // Sử dụng adapter cho RecyclerView thông qua biến binding
                binding.homeNewRec.adapter = NewprAdapter(images)
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

    private fun setupViewFlipper() {
        val viewFlipper = findViewById<ViewFlipper>(R.id.viewFlipper)
        viewFlipper.setInAnimation(applicationContext, android.R.anim.slide_in_left)
        viewFlipper.setOutAnimation(applicationContext, android.R.anim.slide_out_right)

        // Add imageView for each image to viewFlipper.
        for (image in imageList) {
            val imageView = ImageView(this)
            val layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(30, 30, 30, 30)
            layoutParams.gravity = Gravity.CENTER
            imageView.layoutParams = layoutParams
            imageView.setImageResource(image)
            viewFlipper.addView(imageView)
            viewFlipper.setFlipInterval(3000)
            viewFlipper.startFlipping()
        }
    }

    fun shopping(view: View) {
        startActivity(Intent(this@HomeActivity, ShoppingCart::class.java))
    }

    fun profile(view: View) {
        startActivity(Intent(this@HomeActivity, InfoUserActivity::class.java))
    }

    fun notify(view: View) {
        startActivity(Intent(this@HomeActivity, NotifyActivity::class.java))
    }

    fun home(view: View) {
        startActivity(Intent(this@HomeActivity, HomeActivity::class.java))
    }

    fun favourite(view: View) {
        startActivity(Intent(this@HomeActivity, FavouriteActivity::class.java))
    }



    fun onClickHome(position: Int) {
        when(position){
            0 -> startActivity(Intent(this,MonAActivity::class.java))
            1 -> startActivity(Intent(this,MonAuActivity::class.java))
            2 -> startActivity(Intent(this,HaisanActivity::class.java))
            3 -> startActivity(Intent(this,DouongActivity::class.java))
        }
    }

    fun table(view: View) {
        startActivity(Intent(this@HomeActivity, TableActivity::class.java))
    }

    fun party(view: View) {
        startActivity(Intent(this@HomeActivity, PartyActivity::class.java))
    }

    fun map(view: View) {
        startActivity(Intent(this@HomeActivity, MapsActivity::class.java))
    }

    fun search(view: View) {
        startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
    }

    fun contact(view: View) {
        startActivity(Intent(this@HomeActivity, InfoContact::class.java))
    }



}