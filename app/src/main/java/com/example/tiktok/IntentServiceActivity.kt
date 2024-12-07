package com.example.tiktok

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiktok.databinding.ActivityDialogsBinding
import com.example.tiktok.databinding.ActivityIntentServiceBinding

class IntentServiceActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntentServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                startService(it)
                binding.tvserviceinfo.text = "Service running"
            }
        }
        binding.stop.setOnClickListener {
            Intent(this,MyService::class.java).also {
                stopService(it)
                binding.tvserviceinfo.text = "Service stopped"
        }
        binding.start.setOnClickListener {
            Intent(this,MyService::class.java).also {
                startService(it)
                //it is intent
                binding.tvserviceinfo.text = "Service running"
            }
            binding.sendata.setOnClickListener {
                Intent(this, MyService::class.java).also {
                    val dataString = binding.editTextText5.text.toString()
                    it.putExtra("EXTRA_DATA", dataString)
                    startService(it)
                    //it is intent

                }
            }}}}
        }

