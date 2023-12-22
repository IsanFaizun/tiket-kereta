package com.example.travel.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.travel.R
import com.example.travel.databinding.ActivityUserPageBinding

class UserPageActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUserPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val navController = findNavController(R.id.user_nav_host_fragment)
            userBottomNavView.setupWithNavController(navController)
        }
    }
}