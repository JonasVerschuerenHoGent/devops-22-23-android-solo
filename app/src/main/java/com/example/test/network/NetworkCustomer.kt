package com.example.test.network

import com.example.test.domain.Customer
import com.example.test.domain.Department
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkCustomer (
    val id: Int,
    val name: String,
    val email: String,
    @Json(name = "phoneNumber") val phoneNr: String,
    val department: String,
    val externType: String,
    val education: String,
    val backupContactId: Int,
)



//Convert network to domain
@JsonClass(generateAdapter = true)
data class NetworkCustomerContainer(val customers: List<NetworkCustomer>)

fun NetworkCustomerContainer.asDomainModel(): List<Customer> {
    return customers.map {
        Customer(
            id = it.id,
            name = it.name,
            email = it.email,
            phoneNr = it.phoneNr,
            department = asDomainDepartment(it.department),
            externType = it.externType,
            education = it.education,
            backupContactId = it.backupContactId,
        )
    }
}

fun asDomainDepartment(departmentString: String) : Department {
    when(departmentString.lowercase()) {
        "dbo" -> return Department.DBO
        "dbt" -> return Department.DBT
        "dgz" -> return Department.DGZ
        "dit" -> return Department.DIT
        "dlo" -> return Department.DLO
        "dog" -> return Department.DOG
        "dsa" -> return Department.DSA
        else -> return Department.Extern
    }
}