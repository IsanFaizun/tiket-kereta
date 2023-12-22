package com.example.travel.User

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travel.Adapter.TravelAdapter
import com.example.travel.Models.Order
import com.example.travel.Models.Travel
import com.example.travel.databinding.FragmentHistoryBinding
import com.google.firebase.firestore.FirebaseFirestore

class HistoryFragment : Fragment() {
    private lateinit var travelAdapter: TravelAdapter
    private val travelList = mutableListOf<Travel>()
    private val binding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userEmail = sharedPrefs.getString("email", "")

        // Mengambil daftar travel ID berdasarkan email pengguna dari koleksi "Order"
        // Di dalam onViewCreated() setelah melakukan pengecekan userEmail
        if (userEmail != null) {
            val firebase = FirebaseFirestore.getInstance()
            firebase.collection("orders")
                .whereEqualTo("user_email", userEmail)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val order = document.toObject(Order::class.java)
                            val travelId = order.travel_id
                            if (travelId.isNotEmpty()) {
                                Log.d("HistoryFragment", "Travel ID found: $travelId")
                                getTravelDetails(travelId)
                            } else {
                                Log.d("HistoryFragment", "Empty travel ID")
                            }
                        }
                    } else {
                        Log.e("HistoryFragment", "Fetching order data failed")
                    }
                }
        }

    }

    private fun getTravelDetails(travelId: String) {
        val firebase = FirebaseFirestore.getInstance()
        firebase.collection("travels").document(travelId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val travel = document.toObject(Travel::class.java)
                    if (travel != null) {
                        Log.d("HistoryFragment", "Travel details retrieved for ID: $travelId")
                        travelList.add(travel)
                        updateRecyclerView()
                    } else {
                        Log.e("HistoryFragment", "Failed to convert document to Travel object")
                    }
                } else {
                    Log.d("HistoryFragment", "Document does not exist for ID: $travelId")
                }
            }
            .addOnFailureListener { e ->
                Log.e("HistoryFragment", "Error fetching document for ID: $travelId, Error: $e")
            }
    }

    private fun updateRecyclerView() {
        // Inisialisasi atau update adapter dengan travelList
        travelAdapter = TravelAdapter(travelList, {findNavController()}, requireContext())
        binding.travelRv.adapter = travelAdapter
        travelAdapter.notifyDataSetChanged()
    }
}