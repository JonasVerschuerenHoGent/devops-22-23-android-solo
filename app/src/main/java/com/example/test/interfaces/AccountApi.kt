package com.example.test.interfaces

import com.example.test.domain.Customer
import com.example.test.domain.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {


    @GET("accounts/projects/")
    fun getProjectsByUserID() : Call<List<Project>>
    @GET("accounts/")
    fun getAllAccounts() : Call<List<Customer>>
    @GET("accounts/{role}")
    fun getProjects(@Path("role") role:String) : Call<List<Customer>>
}