package com.example.test

data class Account(
    var id: Int,
    var email: String,
    var name: String,
    var department: Department,
    var education: String,
    var externType: String?,
    var phoneNr: String,
    var role: Role
)