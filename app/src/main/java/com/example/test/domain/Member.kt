package com.example.test.domain

data class Member(
    var id: Int,
    var name: String,
    var actief: Boolean,
    var email: String,
    var phoneNr: String,
    var department: Department,
    var role: Role,
)