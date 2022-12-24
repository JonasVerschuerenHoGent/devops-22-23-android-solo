package com.example.test.database.project

import com.example.test.domain.Account
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.Department
import com.example.test.domain.Project
import com.example.test.domain.Role

@Entity(tableName = "project_table")
data class DatabaseProject(

@PrimaryKey(autoGenerate = true)
var id : Int = 0,

@ColumnInfo(name = "project_name")
var name : String

)

// Convert a single DatabaseCustomer into a normal domain Customer
fun DatabaseProject.asDomainModel(): Project {
    return Project(
        id = id,
        name = name

    )
}

// Convert a list of DatabaseCustomers in a list of normal domain Customers
fun List<DatabaseProject>.asDomainModel(): List<Project> {
    return map { it.asDomainModel() }
}

