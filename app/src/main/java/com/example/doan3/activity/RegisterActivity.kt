package com.example.doan3.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.doan3.R

import com.example.doan3.retrofit.ApiClient
import com.example.doan3.retrofit.Retro
import com.example.doan3.utils.Utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class RegisterActivity : AppCompatActivity() {
    private lateinit var editTextFullName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPass: EditText
    private lateinit var editTextRePass: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextCountry: EditText
    private lateinit var editTextCity: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextZipcode: EditText
    private lateinit var apiClient: ApiClient
    private lateinit var btnRegister: Button
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
        initControl()

    }
    private fun initView() {
        apiClient = Retro.getInstance(Utils.BASE_URL).create(ApiClient::class.java)
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPass = findViewById(R.id.editTextPass)
        editTextRePass = findViewById(R.id.editTextRePass)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextCountry = findViewById(R.id.editTextCountry)
        editTextCity = findViewById(R.id.editTextCity)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextZipcode = findViewById(R.id.editTextZipcode)
        btnRegister = findViewById(R.id.btnRegister)
        }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
        private fun initControl() {
        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            dangKi()
        }
    }

    private fun dangKi() {
        val str_hoten = editTextFullName.text.toString().trim()
        val str_email = editTextEmail.text.toString().trim()
        val str_pass = editTextPass.text.toString().trim()
        val str_repass = editTextRePass.text.toString().trim()
        val str_phone = editTextPhone.text.toString().trim()
        val str_country = editTextCountry.text.toString().trim()
        val str_city = editTextCity.text.toString().trim()
        val str_address = editTextAddress.text.toString().trim()
        val str_zipcode = editTextZipcode.text.toString().trim()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


        if (TextUtils.isEmpty(str_hoten)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập Họ và Tên", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(str_email)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(str_phone)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập Số điện thoại", Toast.LENGTH_SHORT).show()
        } else if (!str_email.matches(emailPattern.toRegex())) {
            Toast.makeText(applicationContext, "Email không hợp lệ", Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(str_pass)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập Mật Khẩu", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(str_country)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập Country", Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(str_city)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập City", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(str_address)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập Address", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(str_zipcode)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập zipcode", Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(str_repass)) {
            Toast.makeText(applicationContext, "Bạn chưa nhập lại Mật Khẩu", Toast.LENGTH_SHORT)
                .show()
        }  else {
            if (str_pass == str_repass) {
//post data
                compositeDisposable.add(apiClient.register(str_hoten, str_email, str_phone ,str_pass,str_country, str_city, str_address, str_zipcode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ userModel ->
                        if (userModel.success) {
                            Utils.user_current.name = str_hoten
                            Utils.user_current.password = str_pass
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {

//                            AlertDialog.Builder(this)
//                                .setTitle("Error")
//                                .setMessage(userModel.message)
//                                .setPositiveButton(android.R.string.ok, null)
//                                .setIcon(android.R.drawable.ic_dialog_alert)
//                                .show()
                            Toast.makeText(
                                applicationContext,
                                userModel.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) { throwable ->
                        Toast.makeText(
                            applicationContext,
                            throwable.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
            else {
                Toast.makeText(applicationContext, "Mật Khẩu chưa giống nhau", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

        fun login(view: View?) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

}