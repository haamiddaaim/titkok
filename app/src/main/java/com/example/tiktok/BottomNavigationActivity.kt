package com.example.tiktok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tiktok.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstfrag = FirstFragment()
        val secondfrag = SecondFragment()
        val thirdfrag = ThirdFragment()

        setCurrentFragment(firstfrag)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> setCurrentFragment(firstfrag)
                R.id.miMessages -> setCurrentFragment(secondfrag)
                R.id.miProfile -> setCurrentFragment(thirdfrag)
            }
            true
        }
        binding.bottomNavigationView.getOrCreateBadge(R.id.miMessages).apply {
            number = 10
            isVisible = true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}