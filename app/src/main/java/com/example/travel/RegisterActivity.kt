package com.example.travel

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import com.example.travel.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var calendar = Calendar.getInstance()

    companion object {
        const val EXTRA_USERNAME_REG = "extra_username"
        const val EXTRA_EMAIL_REG = "extra_email"
        const val EXTRA_PASSWORD_REG = "extra_password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show/Hide Password
        val passVisibility = binding.passVisibility
        val pass = binding.passwordReg

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

        // Set Date
        binding.calendarDialog.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, R.style.CustomDatePickerTheme, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                binding.ttl.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        // Hitung Umur
        fun calculateAge(dateOfBirth: String): Int {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = Calendar.getInstance()
            val dobDate = dateFormat.parse(dateOfBirth)
            val dobCalendar = Calendar.getInstance()
            dobCalendar.time = dobDate

            var years = currentDate.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)
            val currentMonth = currentDate.get(Calendar.MONTH)
            val dobMonth = dobCalendar.get(Calendar.MONTH)

            if (currentMonth < dobMonth || (currentMonth == dobMonth && currentDate.get(Calendar.DATE) < dobCalendar.get(Calendar.DATE))) {
                years--
            }
            return years
        }


        with(binding){
            // If tombol register diklik
            regButton.setOnClickListener {
                // Memberi pesan error
                if (usernameReg.text.toString().isEmpty()) {
                    Toast.makeText(this@RegisterActivity, "Mohon masukkan username", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (emailReg.text.toString().isEmpty()) {
                    Toast.makeText(this@RegisterActivity, "Mohon masukkan email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (passwordReg.text.toString().isEmpty()) {
                    Toast.makeText(this@RegisterActivity, "Mohon masukkan password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (ttl.text.toString().isEmpty()){
                    Toast.makeText(this@RegisterActivity, "Mohon isi tanggal lahir anda", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val tglLahir = binding.ttl.text.toString()
                val age = calculateAge(tglLahir)

                if (age < 15) {
                    Toast.makeText(this@RegisterActivity, "Anda harus berusia minimal 15 tahun untuk mendaftar.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Untuk pindah ke LoginActivity
                val intentToLogin = Intent(this@RegisterActivity, LoginActivity::class.java)
                intentToLogin.putExtra(EXTRA_USERNAME_REG, usernameReg.text.toString())
                intentToLogin.putExtra(EXTRA_EMAIL_REG, emailReg.text.toString())
                intentToLogin.putExtra(EXTRA_PASSWORD_REG, passwordReg.text.toString())
                startActivity(intentToLogin)
            }
        }
    }
}