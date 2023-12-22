package com.example.travel.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.travel.Admin.AddTravelFragmentDirections
import com.example.travel.Models.Travel
import com.example.travel.databinding.TravelEditBinding
import com.google.firebase.firestore.FirebaseFirestore

class ManageTravelAdapter(private var listTravel: List<Travel>)
    : RecyclerView.Adapter<ManageTravelAdapter.EditTravelViewHolder>(){
    private val firestore = FirebaseFirestore.getInstance()
    private val travelCollection = firestore.collection("travels")
    inner class EditTravelViewHolder(private val binding: TravelEditBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun bind(travel: Travel) {
                    with(binding) {
                        namaKereta.text = travel.namaKereta
                        kelasKereta.text = travel.kelas
                        tglJalan.text = travel.tglJalan
                        kotaAsal.text = travel.kotaAsal
                        kotaTujuan.text = travel.kotaTujuan
                        stasiunAsal.text = travel.stasiunAsal
                        stasiunTujuan.text = travel.stasiunAsal
                        price.text = "Rp ${travel.harga},00"

                        btnEdit.setOnClickListener {
                            val action = AddTravelFragmentDirections.actionAddTravelFragmentToTravelFormFragment()
                            itemView.findNavController().navigate(action)
                        }
                        btnDelete.setOnClickListener {
                            deleteTravel(travel.id)
                        }
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTravelViewHolder {
        val binding = TravelEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EditTravelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTravel.size
    }

    override fun onBindViewHolder(holder: EditTravelViewHolder, position: Int) {
        holder.bind(listTravel[position])
    }

    fun deleteTravel(travelId: String){
        travelCollection.document(travelId)
            .delete()
    }
}