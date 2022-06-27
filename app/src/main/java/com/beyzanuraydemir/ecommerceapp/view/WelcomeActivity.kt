package com.beyzanuraydemir.ecommerceapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beyzanuraydemir.ecommerceapp.auth.LoginActivity
import com.beyzanuraydemir.ecommerceapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.continueButton.setOnClickListener {
             startActivity(Intent(this, MainPageActivity::class.java))
        }
    }
}