package com.example.test.database.customer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.Customer
import com.example.test.domain.Department

@Entity(tableName = "customer_table")
data class DatabaseCustomer(

    @PrimaryKey
    var id : Int,

    @ColumnInfo(name = "customer_name")
    var name: String,

    @ColumnInfo(name = "customer_email")
    var email : String,

    @ColumnInfo(name = "customer_phone_nr")
    var phoneNr : String,

    @ColumnInfo(name = "customer_department")
    var department: Int,

    @ColumnInfo(name = "customer_extern_type")
    var externType : String?,

    @ColumnInfo(name = "customer_education")
    var education : String,

    @ColumnInfo(name = "customer_backup_contact_id")
    var backupContactId : Int
)


// Convert a single DatabaseCustomer into a normal domain Customer
fun DatabaseCustomer.asDomainModel(): Customer {
    return Customer(
        id = id,
        name = name,
        email = email,
        phoneNr = phoneNr,
        department = asDomainDepartment(department),
        externType = externType,
        education = education,
        backupContactId = backupContactId
    )
}

// Convert a list of DatabaseCustomers into a list of normal domain Customers
fun List<DatabaseCustomer>.asDomainModel(): List<Customer> {
    return map { it.asDomainModel() }
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