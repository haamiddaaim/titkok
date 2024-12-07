package com.example.tiktok

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiktok.adapter.ViewPagerAdapter
import com.example.tiktok.databinding.ActivityViewPager2Binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager2Activity : AppCompatActivity() {
    lateinit var binding: ActivityViewPager2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = listOf(
            R.drawable.icon_account_circle,
            R.drawable.ic_favorites,
            R.drawable.icon_add,
            R.drawable.facebookicon,
            R.drawable.googleicon,
            R.drawable.ic_settings,
            R.drawable.icon_pause,

            )
        //how to create view pager adapter and set it to our viewpager
        val adapter = ViewPagerAdapter(images)
        binding.viewPager.adapter = adapter

        // binding.viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        //how to make it scroll like instagram

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
            //position + 1 since the postion starts at 0 so +1 makes the first tab tab 1, the second tab tab 2...
        }.attach()
        //.attach() connects the tablayout with viewpager
        //when we swipe with the viewpager the tablayout will also swipe and when tablayout is clicked the viewpager will swipe to the corresponding tab
        //how to extend viewpager activity to connect tablayout with viewpager

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@ViewPager2Activity, "Selected ${tab?.text}", Toast.LENGTH_SHORT)
                    .show()
                //if we are in tab 1 and we click tab 1 again
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(
                    this@ViewPager2Activity,
                    "Unselected ${tab?.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(
                    this@ViewPager2Activity,
                    "Reselected ${tab?.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        //object is a anonymous class of OnTabSelectedListener
        //if we want to respond to tab switch events we need to add this
    }
}