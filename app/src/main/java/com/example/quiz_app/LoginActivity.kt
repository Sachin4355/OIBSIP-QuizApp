package com.example.quiz_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.example.quiz_app.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener{
            Firebase.auth.createUserWithEmailAndPassword(
                binding.email.editText?.text.toString(),
                binding.password.editText?.text.toString()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User Created !!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "User Not Created: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    Log.e("LoginActivity", "User creation failed", task.exception)
                }
            }

        }

    }
}