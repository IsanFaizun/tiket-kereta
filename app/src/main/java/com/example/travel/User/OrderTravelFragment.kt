package com.example.travel.User

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel.Adapter.TravelAdapter
import com.example.travel.Models.Travel
import com.example.travel.databinding.FragmentOrderTravelBinding
import com.google.firebase.firestore.FirebaseFirestore

class OrderTravelFragment : Fragment() {
    private val binding by lazy {
        FragmentOrderTravelBinding.inflate(layoutInflater)
    }
    private val firestore = FirebaseFirestore.getInstance()
    private val travelsCollRef = firestore.collection("travels")
    val travelsLiveData: MutableLiveData<List<Travel>> by lazy {
        MutableLiveData<List<Travel>>()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val travelAdapter = TravelAdapter(emptyList(), { findNavController() }, requireContext())
        binding.travelRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = travelAdapter
        }

        travelsLiveData.observe(viewLifecycleOwner) { travels ->
            // Update adapter data when LiveData changes
            travelAdapter.updateData(travels)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getAllTravels()
    }

    private fun getAllTravels() {
        obeserveTravelChanges()
    }

    private fun obeserveTravelChanges() {
        travelsCollRef.addSnapshotListener{ value, error ->
            if (error != null) {
                Log.d("User OrderTravel", "Error listening: $error")
            }
            val travels = value?.toObjects(Travel::class.java)
            if (travels != null) {
                travelsLiveData.postValue(travels)
            } else {
                Log.d("OrderTravelFragment", "travels object is null")
            }
        }
    }
}