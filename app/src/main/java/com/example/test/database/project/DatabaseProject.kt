package com.example.test.database.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/*
@Entity(tableName = "project_table")
data class DatabaseProject(

@PrimaryKey(autoGenerate = true)
var id : Int = 0,

@ColumnInfo(name = "project_name")
var name : String,

@ColumnInfo(name = "virtualmachines")
var virtualMachines : List<String>

)
*/

// Convert a single DatabaseCustomer into a normal domain Project
/*
fun DatabaseProject.asDomainModel(): Project {
    return Project(
        id = id,
        name = name


    )
}

// Convert a list of DatabaseCustomers in a list of normal domain Customers
fun List<DatabaseProject>.asDomainModel(): List<Project> {
    return map { it.asDomainModel() }
}*/





