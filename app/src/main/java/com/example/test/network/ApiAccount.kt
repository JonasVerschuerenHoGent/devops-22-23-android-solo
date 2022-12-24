package com.example.test.network

import com.example.test.database.Account.DatabaseCustomer
import com.example.test.domain.Account
import com.example.test.domain.Department
import com.example.test.domain.Role
import com.example.test.network.interfaces.AccountApiService
import com.example.test.utils.RetrofitBuilder
import com.squareup.moshi.Json

// Represents an Account coming from the API
data class ApiAccount(
    @Json(name = "id")
    var id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "phone_nr")
    val phoneNr: String,

    @Json(name = "education")
    val education: String,

    @Json(name = "department")
    val department : String,

    @Json(name = "extern_type")
    val externType : String?,

    @Json(name="role")
    val role : String,

    @Json(name = "backup_contact")
    val backupContact : Account //?

)

// Convert a single ApiAccount into a normal domain Account
fun ApiAccount.asDomainAccount(): Account {
    return Account(
    id = id,
    name = name,
    email = email,
    education = education,
    externType = externType,
    phoneNr = phoneNr,
    role = role.asDomainRole(),
    department = department.asDomainDepartment(),
    backupContact = backupContact
    )
}


// Convert a single ApiAccount into a normal database Account
fun ApiAccount.asDatabaseCustomer(): DatabaseCustomer {
    return DatabaseCustomer(
        id = id,
        name = name,
        email = email,
        education = education,
        externType = externType,
        phoneNr = phoneNr,
        role = role,
        department = department,
        backupContact = backupContact.toString()
    )
}

// Container that helps us parsing the api response into multiple domain Accounts
data class ApiAccountContainer(
    @Json(name = "body")
    val apiAccounts: List<ApiAccount>
)


// Convert network result into domain Account
fun ApiAccountContainer.asDomainModels(): List<Account> {
    return apiAccounts.map { it.asDomainAccount() }
}

fun List<ApiAccount>.asDomainModels(): List<Account> {
    return this.map { it.asDomainAccount() }
}

// Convert a list of ApiAccounts into a list of DatabaseAccounts (this can then be used in the insert call as vararg)
fun List<ApiAccount>.asDatabaseCustomer(): Array<DatabaseCustomer> {
    return map { it.asDatabaseCustomer() }.toTypedArray()
}


// Convert network result into DatabaseCustomer
fun ApiAccountContainer.asDatabaseCustomer(): List<DatabaseCustomer> {
    return apiAccounts.map { it.asDatabaseCustomer() }
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