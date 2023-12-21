package com.example.travel.Authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.travel.Admin.AdminPageActivity
import com.example.travel.User.UserPageActivity
import com.example.travel.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val firestore = FirebaseFirestore.getInstance()
    private val usersRefColl = firestore.collection("users")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root

        val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        with(binding){
            val authActivity = requireActivity()

            btnLogin.setOnClickListener {
                if (allFieldsFilled()) {
                    val username = usernameLogin.text.toString()
                    val password = passwordLogin.text.toString()

                    usersRefColl.whereEqualTo("username", username).get().addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val docSnapshot = querySnapshot.documents.get(0)
                            val storedPassword = docSnapshot.getString("password")
                            if (password.equals(storedPassword)) {
                                val email = docSnapshot.id
                                val usn = docSnapshot.getString("username")
                                val role = docSnapshot.getString("role")
                                editor.putString("email", email)
                                editor.putString("username", usn)
                                editor.putString("role", role)
                                editor.apply()

                                if (role == "user") {
                                    val intent = Intent(requireContext(), UserPageActivity::class.java)
                                    startActivity(intent)
                                }
                                else if (role == "admin") {
                                    val intent = Intent(requireContext(), AdminPageActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                            else {
                                Toast.makeText(requireContext(), "Username atau password salah", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }.addOnFailureListener {
                        Toast.makeText(requireContext(), "Username atau password salah", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Mohon isi semua kolom", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

    private fun allFieldsFilled(): Boolean {
        return binding.usernameLogin.text.isNotEmpty() && binding.passwordLogin.text.isNotEmpty()
    }
}