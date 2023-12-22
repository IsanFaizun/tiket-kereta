package com.example.travel.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.travel.R
import com.example.travel.databinding.ActivityAdminPageBinding

class AdminPageActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdminPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val navController = findNavController(R.id.admin_nav_host_fragment)
            adminBottomNavView.setupWithNavController(navController)
        }
    }
}