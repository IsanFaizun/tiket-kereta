package com.example.travel.Models

data class Order(
    var id: String = "",
    var title: String = "",
    var user_email: String = "",
    var travel_id: String = "",
    var selectedPaket: List<String> = listOf(),
    var totalPrice: Int = 0
)
