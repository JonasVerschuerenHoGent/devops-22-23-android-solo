package com.example.test.network

import com.example.test.domain.Member
import com.example.test.domain.Role
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class NetworkMember(
    val id: Int,
    val name: String,
    val active: Boolean,
    val email: String,
    @Json(name = "phoneNumber") val phoneNr: String,
    val department: String,
    val role: String,
)




//Convert network to domain
@JsonClass(generateAdapter = true)
data class NetworkMemberContainer(val members: List<NetworkMember>)

fun NetworkMemberContainer.asDomainModel(): List<Member> {
    return members.map {
        Member(
            id = it.id,
            name = it.name,
            active = it.active,
            email = it.email,
            phoneNr = it.phoneNr,
            department = asDomainDepartment(it.department),
            role = asDomainRole(it.role)
        )
    }
}

fun asDomainRole(roleString: String) : Role {
    when(roleString.lowercase()) {
        "admin" -> return Role.Admin
        "manager" -> return Role.Manager
        "viewer" -> return Role.Viewer
        else -> return Role.Customer
    }
}
