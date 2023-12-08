package com.example.travel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.travel.databinding.ActivityAuthBinding
import com.google.android.material.tabs.TabLayoutMediator

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewPager.adapter = TabAdapter(this@AuthActivity)
            viewPager2 = viewPager

            TabLayoutMediator(tabLayout, viewPager) {
                tab, position ->
                tab.text = when(position) {
                    0 -> "Register"
                    1 -> "Login"
                    else -> ""
                }
            }.attach()
        }
    }
    fun navigateToLogin() {
        viewPager2.setCurrentItem(1, true)
    }
    fun navigateToRegister() {
        viewPager2.setCurrentItem(0, true)
    }
}