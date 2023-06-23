package com.example.doan3.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.doan3.databinding.ActivityLoginBinding
import com.example.doan3.model.ResponseLogin
import com.example.doan3.retrofit.ApiClient
import com.example.doan3.retrofit.Retro
import com.example.doan3.retrofit.RetrofitClient
import com.example.doan3.utils.Utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity: AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    private var email: String = " "
    private var pass: String = " "

    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //kiem tra session
        profil = getSharedPreferences(  "login_session", MODE_PRIVATE)
//        if (profil.getString("username", null) != null) {
//
//                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                finish()
//
//        }

        binding!!.btnLogin.setOnClickListener {
            email = binding!!.etEmail.text.toString()
            pass = binding!!.etPassword.text.toString()

            when {
                email == "" -> {
                    binding!!.etEmail.error = "Email không được để trống"
                }
                pass == "" -> {
                    binding!!.etPassword.error = "Mật khẩu không được để trống"
                }
                else -> {
                    binding!!.loading.visibility = View.VISIBLE
                    getData()

                }
            }
        }
        binding!!.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getData () {

        val api = Retro.getInstance(Utils.BASE_URL).create(ApiClient::class.java)
        api.login( email, pass).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {

                if (response.isSuccessful) {
                    if (response.body()?.response == true) {


                        //tạo phiên


                        response.body()?.payload?.phone?.let {
                            response.body()?.payload?.id?.let { it1 ->
                                response.body()?.payload?.zipcode?.let { it2 ->
                                    getSharedPreferences(  "login_session", MODE_PRIVATE)
                                        .edit()
                                        .putInt("id", it1)
                                        .putString("name", response.body()?.payload?.name)
                                        .putInt("phone", response.body()?.payload?.phone!!)
                                        .putString("email", response.body()?.payload?.email)
                                        .putString("address", response.body()?.payload?.address)
                                        .putString("country", response.body()?.payload?.country)
                                        .putString("city", response.body()?.payload?.city)
                                        .putInt("zipcode", it2)
                                        .apply()
                                }
                            }
                        }

                            binding!!.loading.visibility = View.GONE
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()



                    } else {
                        binding!!.loading.visibility = View.GONE
                        Toast.makeText(
                            this@LoginActivity,
                            "Đăng nhập không thành công, Kiểm tra lại tên người dùng và mật khẩu",
                            Toast.LENGTH_LONG
                        ) .show()
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Đăng nhập không thành công, xảy ra lỗi",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }

        })
    }
}



