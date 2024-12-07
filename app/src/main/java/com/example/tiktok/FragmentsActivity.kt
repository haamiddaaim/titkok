package com.example.tiktok

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tiktok.databinding.ActivityDialogsBinding
import com.example.tiktok.databinding.ActivityFragmentsBinding

class FragmentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFragmentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstfrag = FirstFragment()
        val secondfrag = SecondFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framelayFragment,firstfrag)
            commit()
            // replace() means you want to replace this container view id which is the framelayout with first fragment
//this is kotlin scope fun so we can always refer directly to support fragment manager
        }
        //how to use a fragment transation to switch in between two fragments


        //fun that replaces the current fragment with a new one
        binding.btnFrag1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayFragment, firstfrag)
                addToBackStack(null)
                //when
                commit()
            }
        }
            binding.btnFrag2.setOnClickListener {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.framelayFragment, secondfrag)
                    addToBackStack(null)
                    commit()
                }

            }
        }
}