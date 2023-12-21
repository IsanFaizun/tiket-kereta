package com.example.travel.Authentication

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.travel.Models.User
import com.example.travel.R
import com.example.travel.User.UserPageActivity
import com.example.travel.databinding.FragmentRegisterBinding
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val firestore = FirebaseFirestore.getInstance()
    private val usersRefColl = firestore.collection("users")

    private var calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val view = binding.root

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

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.CustomDatePickerTheme,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    binding.ttl.setText(selectedDate)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        with(binding){
            val authActivity = requireActivity()

            regButton.setOnClickListener {
                if (allFieldsFilled()) {
                    val username = usernameReg.text.toString()
                    val email = emailReg.text.toString()
                    val password = passwordReg.text.toString()
                    val ttl = ttl.text.toString()

                    usersRefColl.document(email).get().addOnSuccessListener { doc ->
                        if (doc.exists()) {
                            Toast.makeText(requireContext(), "Email sudah terdaftar!", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val user = User(username = username, email = email, password = password, role = "user")
                            regNewUser(user)
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Mohon isi semua data", Toast.LENGTH_SHORT)
                }
            }
        }

        return view
    }

    private fun allFieldsFilled(): Boolean {
        return binding.usernameReg.text.isNotEmpty() && binding.emailReg.text.isNotEmpty() &&
                binding.passwordReg.text.isNotEmpty() && binding.ttl.text.isNotEmpty()
    }

    private fun regNewUser(user: User) {
        usersRefColl.document(user.email).set(user).addOnSuccessListener {
            val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val editor = sharedPrefs.edit()
            editor.putString("email", user.email)
            editor.putString("username", user.username)
            editor.putString("role", user.role)
            editor.apply()

            val intent = Intent(requireContext(), UserPageActivity::class.java)
            startActivity(intent)
        }
    }
}