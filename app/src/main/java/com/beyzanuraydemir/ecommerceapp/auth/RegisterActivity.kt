package com.beyzanuraydemir.ecommerceapp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.beyzanuraydemir.ecommerceapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    //firebase auth
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.backButton.setOnClickListener{
            onBackPressed()
        }

        binding.registerButton.setOnClickListener {
            validateData()
        }
    }

    private var name = ""
    private var email = ""
    private var adress = ""
    private var phone = ""
    private var password = ""


    private fun validateData() {
        //Input Data
        name = binding.nameEditText.text.toString().trim()
        email = binding.emailEditText.text.toString().trim()
        adress = binding.AdressEditText.text.toString().trim()
        phone = binding.phoneEditText.text.toString().trim()
        password = binding.passwordEditText.text.toString().trim()
        val cPassword = binding.confirmPasswordEditText.text.toString().trim()

        if(name.isEmpty()){
            Toast.makeText(this,"Enter your name...",Toast.LENGTH_SHORT).show()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Invalid Email Adress.",Toast.LENGTH_SHORT).show()
        }
        else if(adress.isEmpty()){
            Toast.makeText(this,"Enter your adress...",Toast.LENGTH_SHORT).show()
        }
        else if(phone.isEmpty()){
            Toast.makeText(this,"Enter your phone...",Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty()){
            Toast.makeText(this,"Enter password...",Toast.LENGTH_SHORT).show()
        }
        else if(cPassword.isEmpty()){
            Toast.makeText(this,"Confirm password...",Toast.LENGTH_SHORT).show()
        }
        else if(password != cPassword){
            Toast.makeText(this,"Password does not match...",Toast.LENGTH_SHORT).show()
        }
        else{
            createUserAccount()
        }

    }

    private fun createUserAccount() {
        //create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                updateUserInfo()
            }
            .addOnFailureListener { e->
                Toast.makeText(this,"Failed creating account due to ${e.message}",Toast.LENGTH_SHORT).show()

            }
    }

    private fun updateUserInfo() {
       // progressDialog.setMessage("Saving user info...")

        val timestamp = System.currentTimeMillis()
        val uid = firebaseAuth.uid
        val hashMap: HashMap<String,Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["name"] = name
        hashMap["email"] = email
        hashMap["adress"] = adress
        hashMap["phone"] = phone
        hashMap["password"] = password
        hashMap["timestamp"] = timestamp

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!).setValue(hashMap)
            .addOnSuccessListener {
                Toast.makeText(this,"Account created...",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()

            }.addOnFailureListener { e->
                Toast.makeText(this,"Failed saving user info due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

}