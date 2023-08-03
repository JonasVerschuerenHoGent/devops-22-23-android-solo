package com.example.test.network

import com.example.test.database.customer.DatabaseCustomer
import com.example.test.domain.Customer
import com.example.test.domain.Department
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//ALL NETWORK CLASSES NEEDED FOR MOSHI
//For fetching all customers
@JsonClass(generateAdapter = true)
data class NetworkCustomerListWrapper (
    val customers: List<NetworkCustomers>
)
@JsonClass(generateAdapter = true)
data class NetworkCustomers (
    val id: Int,
    val name: String,
    val email: String,
    @Json(name = "phoneNumber") val phoneNr: String,
)

//For fetching one customer
@JsonClass(generateAdapter = true)
data class NetworkCustomerSingleWrapper (
    val customer: NetworkCustomer
)
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


//Convert network to database & domain
@JsonClass(generateAdapter = true)
@Json(name = "customers")
data class NetworkCustomerContainer(val customers: List<NetworkCustomer>)

//To database
fun NetworkCustomerContainer.asDatabaseModel(): Array<DatabaseCustomer> {
    return customers.map {
        DatabaseCustomer(
            id = it.id,
            name = it.name,
            email = it.email,
            phoneNr = it.phoneNr,
            department = asDomainDepartment(it.department).first,
            externType = it.externType,
            education = it.education,
            backupContactId = it.backupContactId,
        )
    }.toTypedArray()
}

//To domain
fun NetworkCustomerContainer.asDomainModel(): List<Customer> {
    return customers.map {
        Customer(
            id = it.id,
            name = it.name,
            email = it.email,
            phoneNr = it.phoneNr,
            department = asDomainDepartment(it.department).second,
            externType = it.externType,
            education = it.education,
            backupContactId = it.backupContactId,
        )
    }
}


//helper functions
fun asDomainDepartment(departmentString: String) : Pair<Int, Department> {
    when(departmentString.lowercase()) {
        "dbo" -> return Pair(1, Department.DBO)
        "dbt" -> return Pair(2, Department.DBT)
        "dgz" -> return Pair(3, Department.DGZ)
        "dit" -> return Pair(4, Department.DIT)
        "dlo" -> return Pair(5, Department.DLO)
        "dog" -> return Pair(6, Department.DOG)
        "dsa" -> return Pair(7, Department.DSA)
        else -> return Pair(8, Department.Extern)
    }
}
