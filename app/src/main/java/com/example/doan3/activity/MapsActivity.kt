package com.example.doan3.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.doan3.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapsBinding
    private lateinit var sourceEdt: EditText
    private lateinit var destinationEdt: TextView
    private lateinit var trackBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceEdt = binding.idEdtSource
        destinationEdt = binding.idEdtDestination
        trackBtn = binding.idBtnTrack

        trackBtn.setOnClickListener {
            drawTrack(sourceEdt.text.toString(), destinationEdt.text.toString())
        }
    }

    private fun drawTrack(source: String, destination: String) {
        try {
            val uri = Uri.parse("https://www.google.co.in/maps/dir/$source/$destination")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    fun Back(view: View) {
        startActivity(Intent(this@MapsActivity,HomeActivity::class.java))
    }
}
