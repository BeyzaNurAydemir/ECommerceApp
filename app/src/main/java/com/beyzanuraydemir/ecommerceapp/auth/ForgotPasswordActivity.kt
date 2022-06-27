package com.beyzanuraydemir.ecommerceapp.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.beyzanuraydemir.ecommerceapp.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.submitButton.setOnClickListener {
            validateData()
        }
    }

    private var email = ""
    private fun validateData() {
        email = binding.emailEditText.text.toString().trim()

        if(email.isEmpty()){
            Toast.makeText(this,"Enter email...",Toast.LENGTH_SHORT).show()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Invalid email...",Toast.LENGTH_SHORT).show()
        }else{
            recoverPassword()
        }
    }

    private fun recoverPassword() {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Toast.makeText(this,"Instructions sent to \n$email...",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e->
                Toast.makeText(this,"Failed to send due to ${e.message}",Toast.LENGTH_SHORT).show()

            }
    }
}