package com.example.travel.Models

data class TravelList(
    var id: String = "",
    var title: String = "",
    var tglJalan: String = "",
    var kotaAsal: String = "",
    var kotaTujuan: String = "",
    var stasiunAsal: String = "",
    var stasiunTujuan: String = "",
    var harga: Int = 0,
)
