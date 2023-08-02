package com.example.test.network

import com.example.test.database.member.DatabaseMember
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




//Convert network to database and domain
@JsonClass(generateAdapter = true)
data class NetworkMemberContainer(val members: List<NetworkMember>)

fun NetworkMemberContainer.asDatabaseModel(): Array<DatabaseMember> {
    return members.map {
        DatabaseMember(
            id = it.id,
            name = it.name,
            active = it.active,
            email = it.email,
            phoneNr = it.phoneNr,
            department = asDomainDepartment(it.department).first,
            role = asDomainRole(it.role).first
        )
    }.toTypedArray()
}

fun NetworkMemberContainer.asDomainModel(): List<Member> {
    return members.map {
        Member(
            id = it.id,
            name = it.name,
            active = it.active,
            email = it.email,
            phoneNr = it.phoneNr,
            department = asDomainDepartment(it.department).second,
            role = asDomainRole(it.role).second
        )
    }
}

fun asDomainRole(roleString: String) : Pair<Int, Role> {
    when(roleString.lowercase()) {
        "admin" -> return Pair(1, Role.Admin)
        "manager" -> return Pair(2, Role.Manager)
        "viewer" -> return Pair(3, Role.Viewer)
        else -> return Pair(4, Role.Customer)
    }
}
