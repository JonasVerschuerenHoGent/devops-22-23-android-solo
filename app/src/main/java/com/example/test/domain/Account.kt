package com.example.test.domain

data class Account(
    var id: Int,
    var email: String,
    var name: String,
    var department: Department,
    var education: String,
    var externType: String?,
    var phoneNr: String,
    var role: Role,

    //We have to change backupContact to type ACCOUNT
    //We use string now to mock our data more easily
    var backupContact: String
)