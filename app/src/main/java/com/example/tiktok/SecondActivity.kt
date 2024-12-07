package com.example.tiktok

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiktok.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    //also search for binding with the word activity first
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
//val name = intent.getStringExtra("EXTRA_NAME")
        //we don't need a defalt value for strings since we can return null in java
    //    val age = intent.getIntExtra("EXTRA_AGE",0)
        //you have to provide a name and default value means if something went wrong with the
        // intent or if the extra name doesn't exist it equals to 0
        //ctrl q lets you look at the documentation of the selected fun
        //getIntExtra() returns an integer...in java an int is not nullable but in kotlin it is
        //since getIntExtra() is an fun that was defined in java so we need to provide a default value
        //we can still make a implementation of the int extra fun that doesn't need a defalt value as in kotlin you can make nullable int
//val country = intent.getStringExtra("EXTRA_COUNTRY")

        val person = intent.getSerializableExtra(
                "EXTRA_PERSON") as Person
        //since this returns a serializable we need to cast the result to a person so we use as person
        //we don't need any of the val above since we are using serializable data class
        val personString = "${person.name} is ${person.age} years old and lives in ${person.country}"
        binding.tvperson.text = personString


binding.btnBack.setOnClickListener {
    startActivity(Intent(this, Androidfund::class.java))

    //quick way to clear our current visible activity from the activity stack
}
        binding.btnNextActivity.setOnClickListener {
            Intent(this,ThirdActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}