package com.example.doan3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.doan3.R

class InfoContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_contact)
    }

    fun BackHome(view: View) {
        startActivity(Intent(this@InfoContact, HomeActivity::class.java))
    }
}