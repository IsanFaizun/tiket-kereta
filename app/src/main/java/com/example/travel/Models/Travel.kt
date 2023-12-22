package com.example.travel.Models

data class Travel(
    var id: String = "",
    var namaKereta: String = "",
    var kelas: String = "",
    var tglJalan: String = "",
    var kotaAsal: String = "",
    var kotaTujuan: String = "",
    var stasiunAsal: String = "",
    var stasiunTujuan: String = "",
    var harga: Int = 0,
)
