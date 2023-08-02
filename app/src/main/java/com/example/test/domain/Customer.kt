package com.example.test.domain

data class Customer(
    var id: Int,
    var name: String,
    var email: String,
    var phoneNr: String,
    var department: Department,
    var externType: String?,
    var education: String,
    var backupContactId: Int
)