package com.example.test.domain


data class User(var name: String){

    var user: String = name
    var id: Int = 0
    var email: String = "test@outlook.com"
    var phoneNumber: String = "+32 111 11 11 11"
    var userRole: UserRole = UserRole.Admin
    var department: UserDepartment = UserDepartment.Extern
    var backupContact: User = User("Test User")
    var education: String = "Toegepaste informatica"
    var externType: String = "Extern type test"

}