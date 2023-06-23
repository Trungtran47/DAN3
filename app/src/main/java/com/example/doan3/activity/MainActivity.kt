package com.example.doan3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.doan3.R
import com.example.doan3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun register(view: View?) {
        startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
    }

    fun login(view: View?) {
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
    }
}