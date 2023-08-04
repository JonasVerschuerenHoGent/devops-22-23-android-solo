package com.example.test.database.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.Project

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

    @ColumnInfo(name = "project_vm_amount")
    var vmAmount: Int,

    @ColumnInfo(name = "project_total_cpus")
    var totalCpus: Int,

    @ColumnInfo(name = "project_total_memory")
    var totalMemory: Int,

    @ColumnInfo(name = "project_total_storage")
    var totalStorage: Int,
)


// Convert a single DatabaseProject into a normal domain Project
fun DatabaseProject.asDomainModel(): Project {
    return Project(
        id = id,
        name = name,
        customerId = customerId,
        state = state,
        vmAmount = vmAmount,
        totalCpus = totalCpus,
        totalMemory = totalMemory,
        totalStorage = totalStorage,
    )
}

// Convert a list of DatabaseProjects in a list of normal domain Projects
fun List<DatabaseProject>.asDomainModel(): List<Project> {
    return map { it.asDomainModel() }
}


