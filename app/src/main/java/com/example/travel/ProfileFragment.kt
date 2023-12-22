package com.example.travel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.example.travel.Authentication.AuthActivity
import com.example.travel.Models.User
import com.example.travel.databinding.FragmentProfileBinding
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {
    private val binding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        if (sharedPrefs.contains("username") && sharedPrefs.contains("email") && sharedPrefs.contains("role")) {
            with(binding) {
                usernameMain.text = sharedPrefs.getString("username", "")
                emailMain.text = sharedPrefs.getString("email", "")
                roleMain.text = sharedPrefs.getString("role", "")
            }
        }
        with(binding) {
            logout.setOnClickListener {
                editor.remove("username")
                editor.remove("email")

                editor.remove("role")
                editor.apply()
                val intent = Intent(activity, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
        }
        return binding.root
    }
}