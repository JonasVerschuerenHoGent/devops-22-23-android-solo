package com.example.test.database.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.test.database.customer.CustomerDatabaseDao
import com.example.test.database.customer.DatabaseCustomer
import com.example.test.database.customer.asDomainModel
import com.example.test.domain.Customer
import com.example.test.domain.Department
import com.example.test.domain.Project
import com.example.test.domain.VirtualMachine

@Entity(tableName = "project_table")
data class DatabaseProject(

    @PrimaryKey
    var id : Int,

    @ColumnInfo(name = "project_name")
    var name : String,

    @ColumnInfo(name = "project_customer_id")
    var customerId : Int,

    @ColumnInfo(name = "project_state")
    var state: String,
)


// Convert a single DatabaseProject into a normal domain Project
fun DatabaseProject.asDomainModel(): Project {
    return Project(
        id = id,
        name = name,
        customerId = customerId,
        state = state
    )
}

// Convert a list of DatabaseProjects in a list of normal domain Projects
fun List<DatabaseProject>.asDomainModel(): List<Project> {
    return map { it.asDomainModel() }
}


