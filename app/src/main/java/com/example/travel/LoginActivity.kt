package com.example.travel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import com.example.travel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityLoginBinding

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show/Hide Password
        val passVisibility = binding.passVisibility
        val pass = binding.passwordLogin

        passVisibility.setOnClickListener {
            if (pass.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                // Atur menjadi teks tersembunyi (password)
                pass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                passVisibility.setImageResource(R.drawable.visibility_off_24)
            } else {
                // Atur menjadi teks terlihat
                pass.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passVisibility.setImageResource(R.drawable.visibility_24)
            }
        }

        with(binding){
            val expectedUsername = intent.getStringExtra(RegisterActivity.EXTRA_USERNAME_REG) ?: ""
            val expectedEmail = intent.getStringExtra(RegisterActivity.EXTRA_EMAIL_REG) ?: ""
            val expectedPassword = intent.getStringExtra(RegisterActivity.EXTRA_PASSWORD_REG) ?: ""

            // If tombol Login diklik
            loginButton.setOnClickListener {
                val firstEdt = usernameEmailLogin.text.toString()
                val secondEdt = passwordLogin.text.toString()

                if ((firstEdt == expectedUsername||firstEdt == expectedEmail) && secondEdt == expectedPassword){
                    val intentToDashboard = Intent(this@LoginActivity, MainActivity::class.java)
                    // Store Username dan Email ke MainActivity
                    intentToDashboard.putExtra(RegisterActivity.EXTRA_USERNAME_REG, expectedUsername)
                    intentToDashboard.putExtra(RegisterActivity.EXTRA_EMAIL_REG, expectedEmail)
                    startActivity(intentToDashboard)
                }
                else {
                    Toast.makeText(this@LoginActivity, "Username atau Password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}