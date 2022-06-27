package com.beyzanuraydemir.ecommerceapp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.beyzanuraydemir.ecommerceapp.databinding.ActivityLoginBinding
import com.beyzanuraydemir.ecommerceapp.view.MainPageActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.noAccountTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            firebaseAuth.signInWithEmailAndPassword(binding.emailEditText.text.toString(),binding.passwordEditText.text.toString()).addOnCompleteListener { task->
                if(task.isSuccessful){
                    val intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

        binding.forgotTextView.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

    }






}