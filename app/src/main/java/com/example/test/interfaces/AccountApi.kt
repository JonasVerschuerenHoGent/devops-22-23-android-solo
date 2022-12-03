package com.example.test.interfaces

import com.example.test.domain.Account
import com.example.test.domain.Project
import com.example.test.domain.VirtualMachine
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {


    @GET("accounts/projects/")
    fun getProjectsByUserID() : Call<List<Project>>
    @GET("accounts/")
    fun getAllAccounts() : Call<List<Account>>
    @GET("accounts/{role}")
    fun getProjects(@Path("role") role:String) : Call<List<Account>>
}