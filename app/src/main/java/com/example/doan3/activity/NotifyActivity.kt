package com.example.doan3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.doan3.R

class NotifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify)
    }

    fun backhome(view: View) {
        startActivity(Intent(this@NotifyActivity, HomeActivity::class.java))
    }
}