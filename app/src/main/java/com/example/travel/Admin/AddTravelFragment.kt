package com.example.travel.Admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel.Adapter.ManageTravelAdapter
import com.example.travel.Models.Travel
import com.example.travel.R
import com.example.travel.databinding.FragmentAddTravelBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddTravelFragment : Fragment() {
    private val binding by lazy {
        FragmentAddTravelBinding.inflate(layoutInflater)
    }
    private lateinit var travelAdapter: ManageTravelAdapter
    val firestore = FirebaseFirestore.getInstance()
    val travelCollection = firestore.collection("travels")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = binding.addTravelRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchDataFromFirebase()

        with(binding){
            btnAddTravel.setOnClickListener {
                val action = AddTravelFragmentDirections.actionAddTravelFragmentToTravelFormFragment()
                requireView().findNavController().navigate(action)
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun fetchDataFromFirebase(){
        travelCollection.get()
            .addOnSuccessListener { documents ->
                val travelsList = mutableListOf<Travel>()
                for (document in documents) {
                    val travel = document.toObject(Travel::class.java)
                    travelsList.add(travel)
                }

                // Set up the adapter with the obtained data
                travelAdapter = ManageTravelAdapter(travelsList)
                binding.addTravelRv.adapter = travelAdapter
            }
            .addOnFailureListener { exception ->
                // Handle any errors that might occur while fetching data
            }
    }
}