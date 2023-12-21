package com.example.travel.Models

data class User(
    var username: String = "",
    val email: String = "",
    val ttl: String = "",
    var password: String = "",
    var role: String = "user",
)
