package com.example.test.network

import com.example.test.database.project.DatabaseProject
import com.example.test.domain.Project
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//ALL NETWORK CLASSES NEEDED FOR MOSHI
//For fetching project(s)
@JsonClass(generateAdapter = true)
data class NetworkProjectListWrapper(
    val projects: List<NetworkProject>
)
@JsonClass(generateAdapter = true)
data class NetworkProjectSingleWrapper(
    val project: NetworkProject
)
@JsonClass(generateAdapter = true)
data class NetworkProject(
    val id: Int,
    val name: String,
    val customerId: Int,
    val state: String,
    var vmAmount: Int,
    @Json(name = "totalCPUs") var totalCpus: Int,
    var totalMemory: Int,
    var totalStorage: Int,
)


//Convert network to database and domain
@JsonClass(generateAdapter = true)
data class NetworkProjectContainer(val projects: List<NetworkProject>)

fun NetworkProjectContainer.asDatabaseModel(): Array<DatabaseProject> {
    return projects.map {
        DatabaseProject(
            id = it.id,
            name = it.name,
            customerId = it.customerId,
            state = it.state,
            vmAmount = it.vmAmount,
            totalCpus = it.totalCpus,
            totalMemory = it.totalMemory,
            totalStorage = it.totalStorage,
        )
    }.toTypedArray()
}

fun NetworkProjectContainer.asDomainModel(): List<Project> {
    return projects.map {
        Project(
            id = it.id,
            name = it.name,
            customerId = it.customerId,
            state = it.state,
            vmAmount = it.vmAmount,
            totalCpus = it.totalCpus,
            totalMemory = it.totalMemory,
            totalStorage = it.totalStorage,
        )
    }
}