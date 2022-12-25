package com.example.test.network


import com.example.test.domain.Project
import com.example.test.domain.VirtualMachine
import com.squareup.moshi.Json
import org.json.JSONObject

data class ApiProject(

    @Json(name = "project_id")
    var projectId : Int,

    @Json(name = "project_name")
    val projectName: String,

    @Json(name = "virtual_machine_list")
    val virtualMachines : JSONObject
    )

/*fun ApiProject.asDomainProject(): Project {
    return Project(
        id = projectId,
        name = projectName,
        virtualMachines = virtualMachines.asDomainVM()
    )
}

// Container that helps us parsing the api response into multiple domain Projects
data class ApiProjectContainer(
    @Json(name = "body")
    val apiProjects: List<ApiProject>
)

// Convert network result into domain Projects
fun ApiProjectContainer.asDomainModels(): List<Project> {
    return apiProjects.map { it.asDomainProject() }
}

fun List<ApiProject>.asDomainModels(): List<Project> {
    return this.map { it.asDomainProject() }
}

/*fun JSONObject.asDomainVM(): List<VirtualMachine> {

}*/