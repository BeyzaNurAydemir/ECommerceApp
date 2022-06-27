package com.beyzanuraydemir.ecommerceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.beyzanuraydemir.ecommerceapp.R
import com.beyzanuraydemir.ecommerceapp.databinding.ActivityMainPageBinding

class MainPageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav,navHostFragment.navController)
    }
}