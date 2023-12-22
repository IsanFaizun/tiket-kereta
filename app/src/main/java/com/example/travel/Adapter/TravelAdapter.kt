package com.example.travel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.travel.Models.Travel
import com.example.travel.databinding.TravelItemBinding
import com.google.firebase.firestore.FirebaseFirestore

class TravelAdapter(private var listTravel: List<Travel>, private val navControllerProvider: () -> NavController,
                    private val context: Context
) :
    RecyclerView.Adapter<TravelAdapter.ItemTravelViewHolder>() {

    inner class ItemTravelViewHolder(private val binding: TravelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

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

                btnBuy.setOnCheckedChangeListener(null) // Reset listener to avoid unexpected calls
                btnBuy.isChecked = false // Reset toggle button state

                btnBuy.setOnCheckedChangeListener { _, isChecked ->
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val selectedTravel = listTravel[position]
                        if (isChecked) {
                            moveToOrderColl(selectedTravel)
                        } else {
                            removeFromHistoryFragment(selectedTravel)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTravelViewHolder {
        val binding = TravelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTravelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTravel.size
    }

    override fun onBindViewHolder(holder: ItemTravelViewHolder, position: Int) {
        holder.bind(listTravel[position])
    }

    private fun moveToOrderColl(travel: Travel) {
        val sharedPrefs = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userEmail = sharedPrefs.getString("email", "")

        val firebase = FirebaseFirestore.getInstance()

        if (userEmail?.isNotEmpty() == true) {
            val orderData = hashMapOf(
                "title" to travel.namaKereta,
                "user_email" to userEmail,
                "travel_id" to travel.id,
            )
            firebase.collection("orders")
                .add(orderData)
                .addOnSuccessListener { documentReference ->
                    println("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error adding document: $e")
                }
        }
    }

    private fun removeFromHistoryFragment(travel: Travel){

    }

    fun updateData(newList: List<Travel>) {
        val listTravel = newList
        notifyDataSetChanged()
    }
}