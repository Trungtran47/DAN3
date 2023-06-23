package com.example.doan3.activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.R
import com.example.doan3.databinding.ActivityInfoUserBinding
import com.example.doan3.model.ShoppingCartModel
import com.example.doan3.model.User
import com.example.doan3.utils.Utils
import org.json.JSONObject

class InfoUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoUserBinding
    private lateinit var profil: SharedPreferences
    private var imageUri: Uri? = null
    private lateinit var requestQueue: RequestQueue
    private val us = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profil = getSharedPreferences("login_session", MODE_PRIVATE)
        requestQueue = Volley.newRequestQueue(this)
        val id = profil.getInt("id", 0)
        val url =  Utils.BASE_URL + "doan3/LOG/ShowCustomer.php?id=$id"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                Log.d("Response", response.toString()) // Log the response data

                if (response.length() > 0) {
                    val item = response.getJSONObject(0) // Get the first item in the array
                    val name = item.getString("name")
                    val zipcode = item.getString("zipcode")
                    val country = item.getString("country")
                    val city = item.getString("city")
                    val address = item.getString("address")
                    val email = item.getString("email")
                    val phone = item.getString("phone")
                    val customer = User()
                    us.add(customer)
                    binding.textName.text = name
                    binding.textZipcode.text = zipcode
                    binding.textViewCountry.text = country
                    binding.textViewCity.text = city
                    binding.textAddress.text = address
                    binding.textEmail.text = email
                    binding.textPhone.text = phone

                    binding.btnEdit.setOnClickListener{
                        val intent = Intent(this@InfoUserActivity, EditInfoUserActivity::class.java)
                        intent.putExtra("name", name)
                        intent.putExtra("zipcode", zipcode)
                        intent.putExtra("country", country)
                        intent.putExtra("city", city)
                        intent.putExtra("address", address)
                        intent.putExtra("email", email)
                        intent.putExtra("phone", phone)
                        startActivity(intent)

                    }
                }
            },
            { error ->
                Log.e("Error", error.toString())
            }
        )
        requestQueue.add(jsonArrayRequest)

//        binding?.textName?.text = profil.getString("name", null)
        binding.textId.text = profil.getInt("id", 0).toString()
//        binding?.textPhone?.text = profil.getInt("phone",0).toString()
//        binding?.textAddress?.text = profil.getString("address", null)
//        binding?.textEmail?.text = profil.getString("email", null)
//        binding?.textViewCountry?.text = profil.getString("country", null)
//        binding?.textViewCity?.text = profil.getString("city", null)
//        binding?.textZipcode?.text = profil.getInt("zipcode",0).toString()

//        Picasso.get().load(profil.getString("image", null)).into(binding?.imgProfil)

    }



    fun BackHome(view: View) {
        startActivity(Intent(this@InfoUserActivity, HomeActivity::class.java))
    }

    fun log(view: View) {
        startActivity(Intent(this@InfoUserActivity, MainActivity::class.java))
    }


}