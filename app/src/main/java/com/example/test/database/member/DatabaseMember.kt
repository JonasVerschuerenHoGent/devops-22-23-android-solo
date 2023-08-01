package com.example.test.database.member

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.Member
import com.example.test.domain.Department
import com.example.test.domain.Role

@Entity(tableName = "member_table")
data class DatabaseMember(

    @PrimaryKey
    var id : Int,

    @ColumnInfo(name = "member_name")
    var name: String,

    @ColumnInfo(name = "member_active")
    var active : Boolean,

    @ColumnInfo(name = "member_email")
    var email : String,

    @ColumnInfo(name = "member_phone_nr")
    var phoneNr : String,

    @ColumnInfo(name = "member_role")
    var role: Int,

    @ColumnInfo(name = "member_department")
    var department : Int,
)


// Convert a single DatabaseMember into a normal domain Member
fun DatabaseMember.asDomainModel(): Member {
    return Member(
        id = id,
        name = name,
        active = active,
        email = email,
        phoneNr = phoneNr,
        role = asDomainRole(role),
        department = asDomainDepartment(department),
    )
}

// Convert a list of DatabaseMembers into a list of normal domain Members
fun List<DatabaseMember>.asDomainModel(): List<Member> {
    return map { it.asDomainModel() }
}

fun asDomainRole(roleNr: Int) : Role {
    when(roleNr) {
        1 -> return Role.Admin
        2 -> return Role.Manager
        3 -> return Role.Viewer
        else -> return Role.Customer
    }
}

fun asDomainDepartment(departmentNr: Int) : Department {
    when(departmentNr) {
        1 -> return Department.DBO
        2 -> return Department.DBT
        3 -> return Department.DGZ
        4 -> return Department.DIT
        5 -> return Department.DLO
        6 -> return Department.DOG
        7 -> return Department.DSA
        else -> return Department.Extern
    }
}