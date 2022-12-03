package com.example.test.interfaces

import com.example.test.domain.Project
import com.example.test.domain.VirtualMachine
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectApi {


    @GET("projects/{id}")
    fun getProjectByID(@Path("id") id:String) : Call<Project>
    @GET("virtualmachinesByProject/{projectId}")
    fun getGetVirtualMachinesByProject(@Path("id") id:String) : Call<List<VirtualMachine>>
    @GET("projects/")
    fun getProjects() : Call<List<Project>>
}