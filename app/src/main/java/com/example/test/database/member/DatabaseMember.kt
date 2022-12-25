package com.example.test.database.member

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.Member
import com.example.test.domain.Department
import com.example.test.domain.Role

@Entity(tableName = "member_table")
data class DatabaseMember(

@PrimaryKey(autoGenerate = true)
var id : Int = 0,

@ColumnInfo(name = "member_name")
var name: String,

@ColumnInfo(name = "member_mail")
var email : String,

@ColumnInfo(name = "member_role")
var role: String,

@ColumnInfo(name = "member_phone_nr")
var phoneNr : String,

@ColumnInfo(name = "member_department")
var department : String,

@ColumnInfo(name = "member_active")
var active : String
)

// Convert a single Databasemember into a normal domain member
fun DatabaseMember.asDomainModel(): Member {
    return Member(
        id = id,
        name = name,
        email = email,
        phoneNr = phoneNr,
        role = role.asDomainRole(),
        department = department.asDomainDepartment(),
        active = active.asDomainBoolean()
    )
}

// Convert a list of DatabaseMembers in a list of normal domain members
fun List<DatabaseMember>.asDomainModel(): List<Member> {
    return map { it.asDomainModel() }
}

fun String.asDomainRole() : Role {
    if (lowercase().equals("admin")) {
        return Role.Admin
    } else if (lowercase().equals("manager")) {
        return Role.Manager
    } else if (lowercase().equals("Viewer")) {
        return Role.Viewer
    }
    return Role.Customer

}

fun String.asDomainDepartment() : Department {
    if (lowercase().equals("dbo")) {
        return Department.DBO
    } else if (lowercase().equals("dbt")) {
        return Department.DBT
    } else if (lowercase().equals("dgz")) {
        return Department.DGZ
    } else if (lowercase().equals("dit")) {
        return Department.DIT
    } else if (lowercase().equals("dlo")) {
        return Department.DLO
    } else if (lowercase().equals("dog")) {
        return Department.DOG
    } else if (lowercase().equals("dsa")) {
        return Department.DSA
    }
    return Department.Extern
}
fun String.asDomainBoolean() : Boolean {
    if (lowercase().equals("true")) {
        return true
    }
    return false
}